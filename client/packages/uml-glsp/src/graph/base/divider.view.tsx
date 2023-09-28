/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { containerFeature, RenderingContext, SCompartmentView, svg } from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { NamedElement } from '../../uml/elements/index';

export class SDivider extends NamedElement {
    static override readonly DEFAULT_FEATURES = [...NamedElement.DEFAULT_FEATURES, containerFeature];
}
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class SDividerView extends SCompartmentView {
    override render(element: SDivider, context: RenderingContext): VNode {
        const view: any = (
            <g>
                <path class-uml-comp-separator d={`M 0,0  L ${element.bounds.width},0`}></path>
                {context.renderChildren(element)}
            </g>
        );
        return view;
    }
}
