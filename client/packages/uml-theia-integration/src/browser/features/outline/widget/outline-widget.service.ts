/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import { inject, injectable } from '@theia/core/shared/inversify';
import { OutlineViewService } from '@theia/outline-view/lib/browser/outline-view-service';

import { OutlineWidgetFactory, OutlineWidgetSymbolInformationNode } from './outline-widget.widget';

@injectable()
export class OutlineWidgetService extends OutlineViewService {
    override id = 'diagram-outline-view';

    constructor(@inject(OutlineWidgetFactory) protected override factory: OutlineWidgetFactory) {
        super(factory);
    }

    override publish(roots: OutlineWidgetSymbolInformationNode[]): void {
        if (this.widget) {
            this.widget.setOutlineTree(roots);
        }
        this.onDidChangeOutlineEmitter.fire(roots);
    }
}