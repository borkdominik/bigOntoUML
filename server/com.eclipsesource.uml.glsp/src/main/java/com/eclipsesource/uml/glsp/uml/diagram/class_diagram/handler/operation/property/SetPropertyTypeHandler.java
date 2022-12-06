/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.uml2.uml.Property;

import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.constants.ClassTypes;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertyLabelTypeSuffix;
import com.eclipsesource.uml.glsp.uml.handler.operations.directediting.BaseLabelEditHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.SetPropertyTypeContribution;

public class SetPropertyTypeHandler extends BaseLabelEditHandler<Property> {

   public SetPropertyTypeHandler() {
      super(ClassTypes.LABEL_PROPERTY_TYPE, PropertyLabelTypeSuffix.SUFFIX);
   }

   @Override
   protected CCommand command(final Property element, final String newText) {
      return SetPropertyTypeContribution.create(element, newText);
   }

}
