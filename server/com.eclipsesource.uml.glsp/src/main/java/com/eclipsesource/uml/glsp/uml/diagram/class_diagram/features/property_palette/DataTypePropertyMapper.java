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

import org.eclipse.uml2.uml.DataType;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.features.property_palette.handler.action.UpdateElementPropertyAction;
import com.eclipsesource.uml.glsp.features.property_palette.model.PropertyPalette;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.constants.UmlClass_DataType;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.data_type.UpdateDataTypeNameHandler;
import com.eclipsesource.uml.glsp.uml.features.property_palette.BaseDiagramElementPropertyMapper;

public class DataTypePropertyMapper extends BaseDiagramElementPropertyMapper<DataType> {

   @Override
   public PropertyPalette map(final DataType source) {
      var elementId = idGenerator.getOrCreateId(source);

      var items = propertyBuilder(elementId)
         .text(UmlClass_DataType.Property.NAME, "Name", source.getName())
         .items();

      return new PropertyPalette(elementId, source.getName(), items);
   }

   @Override
   public Optional<UpdateOperation> map(final UpdateElementPropertyAction action) {
      return operationBuilder()
         .map(UmlClass_DataType.Property.NAME,
            (element, op) -> handlerMapper.asOperation(
               UpdateDataTypeNameHandler.class,
               element,
               new UpdateDataTypeNameHandler.Args(op.getValue())))
         .find(action);
   }

}