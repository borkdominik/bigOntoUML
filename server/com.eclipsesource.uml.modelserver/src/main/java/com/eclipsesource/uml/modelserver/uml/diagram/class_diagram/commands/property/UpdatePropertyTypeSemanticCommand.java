/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property;

import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseSemanticElementCommand;

public final class UpdatePropertyTypeSemanticCommand extends BaseSemanticElementCommand {

   protected Property property;
   protected Type newType;

   public UpdatePropertyTypeSemanticCommand(final ModelContext context,
      final Property property,
      final Type newType) {
      super(context);
      this.property = property;
      this.newType = newType;
   }

   @Override
   protected void doExecute() {
      property.setType(newType);
   }

}
