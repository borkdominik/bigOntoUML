/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import {
    CursorCSS,
    cursorFeedbackAction,
    DrawFeedbackEdgeAction,
    EdgeCreationTool,
    EdgeCreationToolMouseListener,
    EnableDefaultToolsAction,
    FeedbackEdgeEndMovingMouseListener,
    findParentByFeature,
    getAbsolutePosition,
    isCtrlOrCmd,
    ISnapper,
    SModelElement,
    TYPES
} from '@eclipse-glsp/client';
import { Action, CreateEdgeOperation, Point, TriggerEdgeCreationAction } from '@eclipse-glsp/protocol';
import { inject, injectable, optional } from 'inversify';
import { sequence } from '../../elements';
import { isSequence } from '../../elements/interacton.model';
import {
    SDDrawFeedbackPositionedEdgeAction,
    SDFeedbackPositionedEdgeEndMovingMouseListener,
    SDRemoveFeedbackPositionedEdgeAction
} from '../tool-feedback/creation-tool-feedback.extension';

// TODO: Sequence Diagram Specific
/**
 * Tool to create connections in a Diagram, by selecting a source and target node.
 */
@injectable()
export class SDEdgeCreationTool extends EdgeCreationTool {
    @inject(TYPES.ISnapper) @optional() readonly snapper?: ISnapper;

    protected override creationToolMouseListener: SDEdgeCreationToolMouseListener;

    override enable(): void {
        if (this.triggerAction === undefined) {
            throw new TypeError(`Could not enable tool ${this.id}.The triggerAction cannot be undefined.`);
        }
        this.creationToolMouseListener = new SDEdgeCreationToolMouseListener(this.triggerAction, this);
        this.mouseTool.register(this.creationToolMouseListener);
        const extendedEdgeFeedback = [
            'edge:sequence__Message',
            'edge:sequence__reply__Message',
            'edge:sequence__sync__Message',
            'edge:sequence__create__Message',
            'edge:sequence__delete__Message',
            'edge:sequence__found__Message',
            'edge:sequence__lost__Message'
        ];
        if (extendedEdgeFeedback.includes(this.triggerAction.elementTypeId)) {
            this.feedbackEndMovingMouseListener = new SDFeedbackPositionedEdgeEndMovingMouseListener(this.anchorRegistry);
        } else {
            this.feedbackEndMovingMouseListener = new FeedbackEdgeEndMovingMouseListener(this.anchorRegistry);
        }
        this.mouseTool.register(this.feedbackEndMovingMouseListener);
        this.dispatchFeedback([cursorFeedbackAction(CursorCSS.OPERATION_NOT_ALLOWED)]);
    }

    override disable(): void {
        this.mouseTool.deregister(this.creationToolMouseListener);
        this.mouseTool.deregister(this.feedbackEndMovingMouseListener);
        this.deregisterFeedback([SDRemoveFeedbackPositionedEdgeAction.create(), cursorFeedbackAction()]);
    }
}

@injectable()
export class SDEdgeCreationToolMouseListener extends EdgeCreationToolMouseListener {
    protected sourcePosition?: Point;
    protected targetPosition?: Point;

    constructor(protected override triggerAction: TriggerEdgeCreationAction, protected override tool: SDEdgeCreationTool) {
        super(triggerAction, tool);
        // this.proxyEdge = new SEdge();
        // this.proxyEdge.type = triggerAction.elementTypeId;
    }

    protected override reinitialize(): void {
        this.source = undefined;
        this.target = undefined;
        this.sourcePosition = undefined;
        this.targetPosition = undefined;
        this.currentTarget = undefined;
        this.allowedTarget = false;
        this.tool.dispatchFeedback([SDRemoveFeedbackPositionedEdgeAction.create()]);
    }

    override nonDraggingMouseUp(_element: SModelElement, event: MouseEvent): Action[] {
        const result: Action[] = [];
        if (event.button === 0) {
            if (!this.isSourceSelected()) {
                if (this.currentTarget && this.allowedTarget) {
                    const sourcePoint = getAbsolutePosition(_element, event);
                    this.source = this.currentTarget.id;
                    this.sourcePosition = sourcePoint;
                    if (_element.features?.has(sequence) || findParentByFeature(_element, isSequence) !== undefined) {
                        this.tool.dispatchFeedback([
                            SDDrawFeedbackPositionedEdgeAction.create({
                                elementTypeId: this.triggerAction.elementTypeId,
                                sourceId: this.source,
                                sourcePosition: this.sourcePosition
                            })
                        ]);
                    } else {
                        this.tool.dispatchFeedback([
                            DrawFeedbackEdgeAction.create({ elementTypeId: this.triggerAction.elementTypeId, sourceId: this.source })
                        ]);
                    }
                }
            } else {
                if (this.currentTarget && this.allowedTarget) {
                    const targetPoint = getAbsolutePosition(_element, event);
                    this.target = this.currentTarget.id;
                    this.targetPosition = targetPoint;
                }
            }
            if (this.source && this.target && this.sourcePosition && this.targetPosition) {
                if (!event.altKey && this.source !== this.target) {
                    /* Default: horizontal Message*/
                    result.push(
                        CreateEdgeOperation.create({
                            elementTypeId: this.triggerAction.elementTypeId,
                            sourceElementId: this.source,
                            targetElementId: this.target,
                            args: {
                                ...this.triggerAction.args,
                                sourcePosition: this.stringify(this.sourcePosition),
                                targetPosition: this.stringify({ x: this.targetPosition.x, y: this.sourcePosition.y })
                            }
                        })
                    );
                } else {
                    /* Diagonal Message with Alt modifier*/
                    result.push(
                        CreateEdgeOperation.create({
                            elementTypeId: this.triggerAction.elementTypeId,
                            sourceElementId: this.source,
                            targetElementId: this.target,
                            args: {
                                ...this.triggerAction.args,
                                sourcePosition: this.stringify(this.sourcePosition),
                                targetPosition: this.stringify(this.targetPosition)
                            }
                        })
                    );
                }

                if (!isCtrlOrCmd(event)) {
                    result.push(EnableDefaultToolsAction.create());
                } else {
                    this.reinitialize();
                }
            }
        } else if (event.button === 2) {
            result.push(EnableDefaultToolsAction.create());
        }
        return result;
    }
    stringify(point: Point): string {
        return point.x + ',' + point.y;
    }
}
