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
package com.borkdominik.big.glsp.uml.uml.elements.enumeration.features;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.uml2.uml.Enumeration;

import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.features.property_palette.model.ElementPropertyBuilder;
import com.borkdominik.big.glsp.server.features.property_palette.model.ElementPropertyItem;
import com.borkdominik.big.glsp.server.features.property_palette.provider.integrations.BGEMFElementPropertyProvider;
import com.borkdominik.big.glsp.uml.uml.UMLTypes;
import com.borkdominik.big.glsp.uml.uml.elements.enumeration_literal.utils.EnumerationLiteralPropertyPaletteUtils;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class EnumerationPropertyProvider extends BGEMFElementPropertyProvider<Enumeration> {

   public static final String OWNED_LITERALS = "ownedLiterals";

   @Inject
   public EnumerationPropertyProvider(@Assisted final Enumerator representation,
      @Assisted final Set<BGTypeProvider> elementTypes) {
      super(representation, elementTypes, Set.of(OWNED_LITERALS));
   }

   @Override
   public List<ElementPropertyItem> doProvide(final Enumeration element) {
      var elementId = providerContext.idGenerator().getOrCreateId(element);
      var builder = new ElementPropertyBuilder(elementId);

      if (providerContext.elementConfig().has(UMLTypes.ENUMERATION_LITERAL)) {
         builder.reference(
            EnumerationLiteralPropertyPaletteUtils.asReference(
               providerContext,
               elementId,
               OWNED_LITERALS,
               "Owned Literals",
               element.getOwnedLiterals()));
      }

      return builder.items();
   }
}
