/********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.uml.uml.elements.class_;

import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.elements.manifest.integrations.BGEMFNodeElementManifest;
import com.borkdominik.big.glsp.server.features.property_palette.BGPropertyPaletteContribution;
import com.borkdominik.big.glsp.uml.uml.UMLTypes;
import com.borkdominik.big.glsp.uml.uml.elements.attribute_owner.AttributeOwnerPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.elements.class_.features.ClassPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.elements.class_.gmodel.ClassGModelMapper;
import com.borkdominik.big.glsp.uml.uml.elements.classifier.ClassifierPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.elements.named_element.NamedElementLabelEditHandler;
import com.borkdominik.big.glsp.uml.uml.elements.named_element.NamedElementPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.elements.operation_owner.OperationOwnerPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.elements.stereotype.StereotypePropertyProvider;

import java.util.Set;

public class ClassElementManifest extends BGEMFNodeElementManifest {
   public ClassElementManifest(final BGRepresentationManifest manifest) {
      super(manifest, Set.of(UMLTypes.ABSTRACT_CLASS, UMLTypes.CLASS));
   }

   @Override
   protected void configureElement() {
      bindGModelMapper(ClassGModelMapper.class);
      bindConfiguration(ClassConfiguration.class);
      bindCreateHandler(ClassOperationHandler.class);
      bindEditLabel(Set.of(NamedElementLabelEditHandler.class));
      bindPropertyPalette(BGPropertyPaletteContribution.Options.builder()
         .propertyProviders(Set.of(
            NamedElementPropertyProvider.class,
            ClassifierPropertyProvider.class,
            ClassPropertyProvider.class,
            AttributeOwnerPropertyProvider.class,
            OperationOwnerPropertyProvider.class,
            StereotypePropertyProvider.class)));
   }
}
