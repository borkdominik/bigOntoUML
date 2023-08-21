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
package com.eclipsesource.uml.glsp.uml.elements.generalization.features;

import java.util.Optional;

import org.eclipse.uml2.uml.Generalization;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.features.property_palette.handler.action.UpdateElementPropertyAction;
import com.eclipsesource.uml.glsp.features.property_palette.model.PropertyPalette;
import com.eclipsesource.uml.glsp.uml.elements.generalization.GeneralizationConfiguration;
import com.eclipsesource.uml.glsp.uml.elements.generalization.GeneralizationOperationHandler;
import com.eclipsesource.uml.glsp.uml.features.property_palette.RepresentationElementPropertyMapper;
import com.eclipsesource.uml.modelserver.uml.elements.generalization.commands.UpdateGeneralizationArgument;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class GeneralizationPropertyMapper extends RepresentationElementPropertyMapper<Generalization> {

   @Inject
   public GeneralizationPropertyMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public PropertyPalette map(final Generalization source) {
      var elementId = idGenerator.getOrCreateId(source);

      var items = this.propertyBuilder(GeneralizationConfiguration.Property.class, elementId)
         .bool(GeneralizationConfiguration.Property.IS_SUBSTITUTABLE, "Is substitutable", source.isSubstitutable())
         .items();

      return new PropertyPalette(elementId, "Generalization", items);
   }

   @Override
   public Optional<UpdateOperation> map(final UpdateElementPropertyAction action) {
      var property = getProperty(GeneralizationConfiguration.Property.class, action);
      var handler = getHandler(GeneralizationOperationHandler.class, action);
      UpdateOperation operation = null;

      switch (property) {
         case IS_SUBSTITUTABLE:
            operation = handler.withArgument(
               UpdateGeneralizationArgument.by()
                  .isSubstitutable(Boolean.parseBoolean(action.getValue()))
                  .build());
            break;
      }

      return withContext(operation);
   }

}
