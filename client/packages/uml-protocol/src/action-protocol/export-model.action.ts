/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { Action, JsonMap, RequestAction, ResponseAction } from '@eclipse-glsp/protocol';

export interface ExportModelAction extends RequestAction<ExportModelResponseAction> {
    kind: typeof ExportModelAction.KIND;
    options?: JsonMap;
}
export namespace ExportModelAction {
    export const KIND = 'exportModel';

    export function exportModel(jsonModelUri: string): ExportModelAction {
        return {
            kind: KIND,
            options: {
                modelUri: jsonModelUri
            },
            requestId: ''
        };
    }
}

export interface ExportModelResponseAction extends ResponseAction {
    kind: typeof ExportModelResponseAction.KIND;
    sourceUri: string;
}

export namespace ExportModelResponseAction {
    export const KIND = 'exportModelResponse';

    export function is(action: unknown): action is ExportModelResponseAction {
        return Action.hasKind(action, KIND);
    }
}
