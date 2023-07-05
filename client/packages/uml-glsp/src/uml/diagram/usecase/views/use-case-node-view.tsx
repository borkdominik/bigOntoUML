/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
import { RenderingContext, ShapeView, svg } from '@eclipse-glsp/client';
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { LabeledNode } from '../../../../graph/model';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class UseCaseNodeView extends ShapeView {
    render(node: LabeledNode, context: RenderingContext): VNode {
        const rX = Math.max(node.size.width, node.size.height) < 0 ? 0 : Math.max(node.size.width, node.size.height) / 2;
        const rY = Math.min(node.size.width, node.size.height) < 0 ? 0 : Math.min(node.size.width, node.size.height) / 2;
        const lineLength = 2 * rX * Math.sqrt(1 - Math.pow((rY - 48) / rY, 2));
        const lineStart = (rX * 2 - lineLength) / 2;
        const linePath = 'M ' + lineStart + ',48  L ' + (lineStart + lineLength) + ',48';

        const useCaseNode: any = (
            <g class-node={true} class-selected={node.selected} class-mouseover={node.hoverFeedback}>
                <defs>
                    <filter id='dropShadow'>
                        <feDropShadow
                            dx='1.5'
                            dy='1.5'
                            stdDeviation='0.5'
                            style-flood-color='var(--uml-drop-shadow)'
                            style-flood-opacity='0.5'
                        />
                    </filter>
                </defs>
                <ellipse cx={rX} cy={rY} rx={rX} ry={rY} />
                {context.renderChildren(node)}
                {node.children[1] && node.children[1].children.length > 0 ? <path class-uml-comp-separator={true} d={linePath}></path> : ''}
            </g>
        );
        return useCaseNode;
    }
}
