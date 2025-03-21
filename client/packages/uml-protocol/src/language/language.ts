/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
export enum UMLDiagramType {
    NONE = '',
    ACTIVITY = 'ACTIVITY',
    CLASS = 'CLASS',
    COMMUNICATION = 'COMMUNICATION',
    COMPONENT = 'COMPONENT',
    COMPOSITE = 'COMPOSITE',
    DEPLOYMENT = 'DEPLOYMENT',
    INTERACTION = 'INTERACTION',
    OBJECT = 'OBJECT',
    PACKAGE = 'PACKAGE',
    PROFILE = 'PROFILE',
    SEQUENCE = 'SEQUENCE',
    STATE_MACHINE = 'STATE_MACHINE',
    TIMING = 'TIMING',
    USE_CASE = 'USE_CASE',
    INFORMATION_FLOW = 'INFORMATION_FLOW',
    ONTO_CLASS = 'ONTO_CLASS'
}

export namespace UMLDiagramTypeUtil {
    export const supported: UMLDiagramType[] = [
        UMLDiagramType.ACTIVITY,
        UMLDiagramType.CLASS,
        UMLDiagramType.COMMUNICATION,
        UMLDiagramType.DEPLOYMENT,
        UMLDiagramType.INFORMATION_FLOW,
        UMLDiagramType.PACKAGE,
        UMLDiagramType.STATE_MACHINE,
        UMLDiagramType.ONTO_CLASS,
        UMLDiagramType.USE_CASE
    ];

    export function parseString(diagramType: string): UMLDiagramType {
        return Object.values(UMLDiagramType).find(u => u.toUpperCase() === diagramType.toUpperCase()) ?? UMLDiagramType.NONE;
    }
}
