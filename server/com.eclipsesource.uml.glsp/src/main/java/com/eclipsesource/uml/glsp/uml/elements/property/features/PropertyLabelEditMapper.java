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
package com.eclipsesource.uml.glsp.uml.elements.property.features;

import java.util.Optional;

import org.eclipse.glsp.server.features.directediting.ApplyLabelEditOperation;
import org.eclipse.uml2.uml.Property;

import com.eclipsesource.uml.glsp.core.constants.CoreTypes;
import com.eclipsesource.uml.glsp.core.gmodel.suffix.NameLabelSuffix;
import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.uml.elements.property.PropertyConfiguration;
import com.eclipsesource.uml.glsp.uml.elements.property.PropertyOperationHandler;
import com.eclipsesource.uml.glsp.uml.elements.property.gmodel.suffix.PropertyMultiplicityLabelSuffix;
import com.eclipsesource.uml.glsp.uml.elements.property.gmodel.suffix.PropertyTypeLabelSuffix;
import com.eclipsesource.uml.glsp.uml.features.label_edit.BaseLabelEditMapper;
import com.eclipsesource.uml.glsp.uml.utils.MultiplicityUtil;
import com.eclipsesource.uml.modelserver.uml.elements.property.commands.UpdatePropertyArgument;

public class PropertyLabelEditMapper extends BaseLabelEditMapper<Property> {

   @Override
   public Optional<UpdateOperation> map(final ApplyLabelEditOperation operation) {
      var handler = getHandler(PropertyOperationHandler.class, operation);
      UpdateOperation update = null;

      if (matches(operation, CoreTypes.LABEL_NAME, NameLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .name(operation.getText())
               .build());
      } else if (matches(operation, PropertyConfiguration.Label.multiplicityTypeId(),
         PropertyMultiplicityLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .upperBound(MultiplicityUtil.getUpper(operation.getText()))
               .lowerBound(MultiplicityUtil.getLower(operation.getText()))
               .build());
      } else if (matches(operation, PropertyConfiguration.Label.typeTypeId(), PropertyTypeLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .typeId(operation.getText())
               .build());
      }

      return withContext(update);
   }
}