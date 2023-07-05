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
package com.eclipsesource.uml.glsp.uml.diagram.usecase_diagram.handler.operation.property;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.uml2.uml.AttributeOwner;

import com.eclipsesource.uml.glsp.uml.diagram.usecase_diagram.diagram.UmlUseCase_Property;
import com.eclipsesource.uml.glsp.uml.handler.operations.create.BaseCreateChildNodeHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.usecase_diagram.commands.property.CreatePropertyContribution;

public class CreatePropertyHandler extends BaseCreateChildNodeHandler<AttributeOwner> {

   public CreatePropertyHandler() {
      super(UmlUseCase_Property.typeId());
   }

   @Override
   protected CCommand createCommand(final CreateNodeOperation operation, final AttributeOwner parent) {
      return CreatePropertyContribution.create(parent);
   }
}
