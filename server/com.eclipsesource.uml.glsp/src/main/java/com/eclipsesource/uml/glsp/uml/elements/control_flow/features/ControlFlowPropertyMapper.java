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
package com.eclipsesource.uml.glsp.uml.elements.control_flow.features;

import java.util.Optional;

import org.eclipse.uml2.uml.ControlFlow;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.features.property_palette.handler.action.UpdateElementPropertyAction;
import com.eclipsesource.uml.glsp.features.property_palette.model.PropertyPalette;
import com.eclipsesource.uml.glsp.uml.elements.control_flow.ControlFlowConfiguration;
import com.eclipsesource.uml.glsp.uml.elements.control_flow.ControlFlowOperationHandler;
import com.eclipsesource.uml.glsp.uml.features.property_palette.RepresentationElementPropertyMapper;
import com.eclipsesource.uml.modelserver.uml.elements.control_flow.commands.UpdateControlFlowArgument;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ControlFlowPropertyMapper extends RepresentationElementPropertyMapper<ControlFlow> {

   @Inject
   public ControlFlowPropertyMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public PropertyPalette map(final ControlFlow source) {
      var elementId = idGenerator.getOrCreateId(source);

      var guardText = source.getGuard() != null ? source.getGuard().stringValue() : null;
      var weightText = source.getWeight() != null ? Integer.toString(source.getWeight().integerValue()) : null;

      var items = this.propertyBuilder(ControlFlowConfiguration.Property.class, elementId)
         .text(ControlFlowConfiguration.Property.GUARD, "Guard", guardText)
         .text(ControlFlowConfiguration.Property.WEIGHT, "Weight", weightText)
         .items();

      return new PropertyPalette(elementId, "ControlFlow", items);
   }

   @Override
   public Optional<UpdateOperation> map(final UpdateElementPropertyAction action) {
      var property = getProperty(ControlFlowConfiguration.Property.class, action);
      var handler = getHandler(ControlFlowOperationHandler.class, action);

      UpdateOperation operation = null;

      switch (property) {
         case GUARD:
            operation = handler.withArgument(
               UpdateControlFlowArgument.by()
                  .guard(action.getValue().isBlank() ? null : action.getValue())
                  .build());
            break;
         case WEIGHT:
            operation = handler.withArgument(
               UpdateControlFlowArgument.by()
                  .weight(getPositiveInt(action.getValue()))
                  .build());
            break;
      }

      return withContext(operation);
   }

   private Integer getPositiveInt(final String value) {
      try {
         var number = Integer.parseUnsignedInt(value);
         if (number == 0) {
            return null;
         }

         return number;
      } catch (NumberFormatException ex) {
         return null;
      }
   }
}