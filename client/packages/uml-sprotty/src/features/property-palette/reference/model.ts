/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { Action } from "@eclipse-glsp/protocol";

import { ElementPropertyItem } from "../model";

export interface ElementReferencePropertyItem extends ElementPropertyItem {
    type: typeof ElementReferencePropertyItem.TYPE;
    label: string;
    references: ElementReferencePropertyItem.Reference[];
    creates: ElementReferencePropertyItem.CreateReference[];
    isOrderable: boolean;
}

export namespace ElementReferencePropertyItem {
    export const TYPE = "REFERENCE";

    export interface Reference {
        label: string;
        elementId: string;
        isReadonly: boolean;
    }

    export interface CreateReference {
        label: string;
        action: Action;
    }

    export function is(value: ElementPropertyItem): value is ElementReferencePropertyItem {
        return value.type === TYPE;
    }
}