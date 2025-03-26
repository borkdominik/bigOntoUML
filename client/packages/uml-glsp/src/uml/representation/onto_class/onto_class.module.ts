/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { UMLDiagramType } from '@borkdominik-biguml/uml-protocol';
import { ContainerModule } from 'inversify';
import {
    registerAbstractionElement,
    registerAssociationElement,
    registerClassElement,
    registerDataTypeElement,
    registerDependencyElement,
    registerEnumerationElement,
    registerEnumerationLiteralElement,
    registerGeneralizationElement,
    registerInstanceSpecificationElement,
    registerInterfaceElement,
    registerInterfaceRealizationElement,
    registerOperationElement,
    registerPackageElement,
    registerPackageImportElement,
    registerPackageMergeElement,
    registerParameterElement,
    registerPrimitiveTypeElement,
    registerPropertyElement,
    registerRealizationElement,
    registerSlotElement,
    registerSubstitutionElement,
    registerUsageElement
} from '../../elements/index';

export const ontoUmlClassDiagramModule = new ContainerModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };
    registerClassElement(context, UMLDiagramType.ONTO_CLASS);
    registerDataTypeElement(context, UMLDiagramType.ONTO_CLASS);
    registerEnumerationElement(context, UMLDiagramType.ONTO_CLASS);
    registerEnumerationLiteralElement(context, UMLDiagramType.ONTO_CLASS);
    registerInterfaceElement(context, UMLDiagramType.ONTO_CLASS);
    registerOperationElement(context, UMLDiagramType.ONTO_CLASS);
    registerPackageElement(context, UMLDiagramType.ONTO_CLASS);
    registerParameterElement(context, UMLDiagramType.ONTO_CLASS);
    registerPropertyElement(context, UMLDiagramType.ONTO_CLASS);
    registerPrimitiveTypeElement(context, UMLDiagramType.ONTO_CLASS);
    registerSlotElement(context, UMLDiagramType.ONTO_CLASS);
    registerInstanceSpecificationElement(context, UMLDiagramType.ONTO_CLASS);

    registerAbstractionElement(context, UMLDiagramType.ONTO_CLASS);
    registerAssociationElement(context, UMLDiagramType.ONTO_CLASS);
    registerDependencyElement(context, UMLDiagramType.ONTO_CLASS);
    registerInterfaceRealizationElement(context, UMLDiagramType.ONTO_CLASS);
    registerGeneralizationElement(context, UMLDiagramType.ONTO_CLASS);
    registerRealizationElement(context, UMLDiagramType.ONTO_CLASS);
    registerSubstitutionElement(context, UMLDiagramType.ONTO_CLASS);
    registerUsageElement(context, UMLDiagramType.ONTO_CLASS);
    registerPackageImportElement(context, UMLDiagramType.ONTO_CLASS);
    registerPackageMergeElement(context, UMLDiagramType.ONTO_CLASS);
});
