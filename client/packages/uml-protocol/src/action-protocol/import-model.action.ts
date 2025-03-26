/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { Action, JsonMap, RequestAction, ResponseAction } from '@eclipse-glsp/protocol';

export interface ImportModelAction extends RequestAction<ImportModelResponseAction> {
    kind: typeof ImportModelAction.KIND;
    options?: JsonMap;
}
export namespace ImportModelAction {
    export const KIND = 'importModel';

    export function importModel(jsonModelUri: string, filename: string, modelUri: string): ImportModelAction {
        return {
            kind: KIND,
            options: {
                jsonModelUri: jsonModelUri,
                filename: filename,
                modelUri: modelUri
            },
            requestId: ''
        };
    }
}

export interface ImportModelResponseAction extends ResponseAction {
    kind: typeof ImportModelResponseAction.KIND;
    sourceUri: string;
}

export namespace ImportModelResponseAction {
    export const KIND = 'importModelResponse';

    export function is(action: unknown): action is ImportModelResponseAction {
        return Action.hasKind(action, KIND);
    }
}
