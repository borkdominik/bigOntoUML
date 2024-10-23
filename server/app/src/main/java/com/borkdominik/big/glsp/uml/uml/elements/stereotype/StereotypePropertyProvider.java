/********************************************************************************
 * Copyright (c) 2024 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.uml.uml.elements.stereotype;

import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.features.property_palette.handler.BGUpdateElementPropertyAction;
import com.borkdominik.big.glsp.server.features.property_palette.model.ElementChoicePropertyItem;
import com.borkdominik.big.glsp.server.features.property_palette.model.ElementPropertyBuilder;
import com.borkdominik.big.glsp.server.features.property_palette.model.ElementPropertyItem;
import com.borkdominik.big.glsp.server.features.property_palette.provider.integrations.BGEMFElementPropertyProvider;
import com.borkdominik.big.glsp.uml.core.model.UMLSourceModelStorage;
import com.borkdominik.big.glsp.uml.uml.commands.UMLUpdateElementCommand;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.resource.UMLResource;

import java.util.List;
import java.util.Set;

public class StereotypePropertyProvider extends BGEMFElementPropertyProvider<Classifier> {

    public static final String STEREOTYPE = "stereotype";

    @Inject
    public StereotypePropertyProvider(@Assisted final Enumerator representation,
                                      @Assisted final Set<BGTypeProvider> elementTypes) {
        super(representation, elementTypes, Set.of(STEREOTYPE));
    }

    @Override
    public List<ElementPropertyItem> doProvide(final Classifier element) {
        element.getPackage().applyProfile((Profile) UMLSourceModelStorage.metaModel);
        var options = element.getApplicableStereotypes().stream()
                .map(stereotype -> ElementChoicePropertyItem.Choice.builder().value(stereotype.getName()).label(stereotype.getName()).build())
                .toList();

        var elementId = providerContext.idGenerator().getOrCreateId(element);
        var builder = new ElementPropertyBuilder(elementId)
                .choice(STEREOTYPE, "Stereotype", options, element.getAppliedStereotypes().stream().findFirst().map(Stereotype::getName).orElse(""));
        return builder.items();
    }

    @Override
    public Command doHandle(final BGUpdateElementPropertyAction action, final Classifier element) {
        var value = action.getValue();
        var argument = UMLUpdateElementCommand.Argument
                .<Element>updateElementArgumentBuilder()
                .consumer(e -> {
                    switch (action.getPropertyId()) {
                        case STEREOTYPE:
                            ((Type) e).getPackage().applyProfile((Profile) UMLSourceModelStorage.metaModel);
                            e.getAppliedStereotypes().forEach(e::unapplyStereotype);
                            e.applyStereotype(getStereotype(value));
                            break;
                    }
                })
                .build();

        return new UMLUpdateElementCommand<>(context, modelState.getSemanticModel(), element, argument);
    }

    private Stereotype getStereotype(String name) {
        return ((Profile) UMLSourceModelStorage.metaModel).getPackagedElements().stream()
                .filter(packageableElement -> packageableElement.getName().equals(name))
                .findFirst().map(packageableElement -> (Stereotype) packageableElement)
                .get();
    }

}
