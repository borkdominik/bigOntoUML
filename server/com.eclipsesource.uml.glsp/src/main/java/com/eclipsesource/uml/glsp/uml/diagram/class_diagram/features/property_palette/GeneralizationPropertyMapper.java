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
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.features.property_palette;

import java.util.Optional;

import org.eclipse.uml2.uml.Generalization;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.features.property_palette.handler.action.UpdateElementPropertyAction;
import com.eclipsesource.uml.glsp.features.property_palette.model.PropertyPalette;
import com.eclipsesource.uml.glsp.uml.features.property_palette.BaseDiagramElementPropertyMapper;

public class GeneralizationPropertyMapper extends BaseDiagramElementPropertyMapper<Generalization> {

   @Override
   public PropertyPalette map(final Generalization source) {
      var elementId = idGenerator.getOrCreateId(source);

      var items = propertyBuilder(elementId)
         .items();

      return new PropertyPalette(elementId, "Generalization", items);
   }

   @Override
   public Optional<UpdateOperation> map(final UpdateElementPropertyAction action) {
      return operationBuilder()
         .find(action);
   }

}