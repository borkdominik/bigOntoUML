/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.elements.interface_.features;

import java.util.Optional;

import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.VisibilityKind;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.features.property_palette.handler.action.UpdateElementPropertyAction;
import com.eclipsesource.uml.glsp.features.property_palette.model.PropertyPalette;
import com.eclipsesource.uml.glsp.uml.elements.interface_.InterfaceConfiguration;
import com.eclipsesource.uml.glsp.uml.elements.interface_.InterfaceOperationHandler;
import com.eclipsesource.uml.glsp.uml.elements.operation.utils.OperationPropertyPaletteUtils;
import com.eclipsesource.uml.glsp.uml.elements.property.utils.PropertyPropertyPaletteUtils;
import com.eclipsesource.uml.glsp.uml.features.property_palette.RepresentationElementPropertyMapper;
import com.eclipsesource.uml.glsp.uml.utils.element.VisibilityKindUtils;
import com.eclipsesource.uml.modelserver.uml.elements.interface_.commands.UpdateInterfaceArgument;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class InterfacePropertyMapper extends RepresentationElementPropertyMapper<Interface> {

   @Inject
   public InterfacePropertyMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public PropertyPalette map(final Interface source) {
      var elementId = idGenerator.getOrCreateId(source);

      var items = this.propertyBuilder(InterfaceConfiguration.Property.class, elementId)
         .text(InterfaceConfiguration.Property.NAME, "Name", source.getName())
         .bool(InterfaceConfiguration.Property.IS_ABSTRACT, "Is abstract", source.isAbstract())
         .choice(
            InterfaceConfiguration.Property.VISIBILITY_KIND,
            "Visibility",
            VisibilityKindUtils.asChoices(),
            source.getVisibility().getLiteral())
         .reference(PropertyPropertyPaletteUtils.asReference(this, elementId,
            InterfaceConfiguration.Property.OWNED_ATTRIBUTES, "Owned Attribute", source.getOwnedAttributes()))
         .reference(OperationPropertyPaletteUtils.asReference(this, elementId,
            InterfaceConfiguration.Property.OWNED_OPERATIONS, "Owned Operation", source.getOwnedOperations()))
         .items();

      return new PropertyPalette(elementId, source.getName(), items);
   }

   @Override
   public Optional<UpdateOperation> map(final UpdateElementPropertyAction action) {
      var property = getProperty(InterfaceConfiguration.Property.class, action);
      var handler = getHandler(InterfaceOperationHandler.class, action);
      UpdateOperation operation = null;

      switch (property) {
         case NAME:
            operation = handler.withArgument(
               UpdateInterfaceArgument.by()
                  .name(action.getValue())
                  .build());
            break;
         case IS_ABSTRACT:
            operation = handler.withArgument(
               UpdateInterfaceArgument.by()
                  .isAbstract(Boolean.parseBoolean(action.getValue()))
                  .build());
            break;
         case VISIBILITY_KIND:
            operation = handler.withArgument(
               UpdateInterfaceArgument.by()
                  .visibilityKind(VisibilityKind.get(action.getValue()))
                  .build());
            break;
      }

      return withContext(operation);

   }

}
