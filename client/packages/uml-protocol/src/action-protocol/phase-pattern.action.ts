/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { Action, JsonMap, RequestAction, ResponseAction } from '@eclipse-glsp/protocol';

export interface PhasePatternAction extends RequestAction<PhasePatternResponseAction> {
    kind: typeof PhasePatternAction.KIND;
    options?: JsonMap;
    phases: string[];
}
export namespace PhasePatternAction {
    export const KIND = 'phasePattern';

    export function create(idPorvider: string, phases: string[]): PhasePatternAction {
        return {
            kind: KIND,
            options: {
                idPorvider: idPorvider
            },
            phases: phases,
            requestId: ''
        };
    }
}

export interface PhasePatternResponseAction extends ResponseAction {
    kind: typeof PhasePatternResponseAction.KIND;
    sourceUri: string;
}

export namespace PhasePatternResponseAction {
    export const KIND = 'phasePatternResponse';

    export function is(action: unknown): action is PhasePatternResponseAction {
        return Action.hasKind(action, KIND);
    }
}
