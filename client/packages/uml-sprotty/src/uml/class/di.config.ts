/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
import "@eclipse-glsp/client/css/glsp-sprotty.css";
import "sprotty/css/edit-label.css";

import { configureModelElement, PolylineEdgeView, SCompartmentView, SEdge, SLabelView } from "@eclipse-glsp/client/lib";
import { ContainerModule } from "inversify";

import { IconView } from "../../common/common";
import { IconLabelCompartment, SEditableLabel } from "../../model";
import { UmlTypes } from "../../utils";
import { NamedElement } from "../shared/named-element.model";
import { NamedElementView } from "../shared/named-element.view";
import { IconClass } from "./elements/class";
import { IconEnumeration } from "./elements/enumeration";
import { IconProperty } from "./elements/property";

export default function createClassModule(): ContainerModule {
    const classModule = new ContainerModule((bind, unbind, isBound, rebind) => {
        const context = { bind, unbind, isBound, rebind };

        // Class
        configureModelElement(
            context,
            UmlTypes.ICON_CLASS,
            IconClass,
            IconView
        );
        configureModelElement(
            context,
            UmlTypes.CLASS,
            NamedElement,
            NamedElementView
        );
        configureModelElement(
            context,
            UmlTypes.ABSTRACT_CLASS,
            NamedElement,
            NamedElementView
        );

        // Enumeration
        configureModelElement(
            context,
            UmlTypes.ICON_ENUMERATION,
            IconEnumeration,
            IconView
        );
        configureModelElement(
            context,
            UmlTypes.ENUMERATION,
            NamedElement,
            NamedElementView
        );

        // Interface
        configureModelElement(
            context,
            UmlTypes.INTERFACE,
            NamedElement,
            NamedElementView
        );

        // Association
        configureModelElement(
            context,
            UmlTypes.ASSOCIATION,
            SEdge,
            PolylineEdgeView
        );
        configureModelElement(
            context,
            UmlTypes.AGGREGATION,
            SEdge,
            PolylineEdgeView
        );
        configureModelElement(
            context,
            UmlTypes.COMPOSITION,
            SEdge,
            PolylineEdgeView
        );

        // Generalization
        configureModelElement(
            context,
            UmlTypes.CLASS_GENERALIZATION,
            SEdge,
            PolylineEdgeView
        );

        // Property
        configureModelElement(
            context,
            UmlTypes.ICON_PROPERTY,
            IconProperty,
            IconView
        );
        configureModelElement(
            context,
            UmlTypes.PROPERTY,
            IconLabelCompartment,
            SCompartmentView
        );
        configureModelElement(
            context,
            UmlTypes.LABEL_PROPERTY_NAME,
            SEditableLabel,
            SLabelView
        );
        configureModelElement(
            context,
            UmlTypes.LABEL_PROPERTY_TYPE,
            SEditableLabel,
            SLabelView
        );
        configureModelElement(
            context,
            UmlTypes.LABEL_PROPERTY_MULTIPLICITY,
            SEditableLabel,
            SLabelView
        );

        // Other
        configureModelElement(
            context,
            UmlTypes.LABEL_EDGE_MULTIPLICITY,
            SEditableLabel,
            SLabelView
        );
    });

    return classModule;
}
