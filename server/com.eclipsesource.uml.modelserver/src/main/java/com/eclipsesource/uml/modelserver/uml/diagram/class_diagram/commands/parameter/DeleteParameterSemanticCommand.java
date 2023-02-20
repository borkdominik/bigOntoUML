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
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.parameter;

import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseDeleteSemanticChildCommand;

public final class DeleteParameterSemanticCommand extends BaseDeleteSemanticChildCommand<Operation, Parameter> {

   public DeleteParameterSemanticCommand(final ModelContext context, final Parameter semanticElement) {
      super(context, semanticElement.getOperation(), semanticElement);
   }

   @Override
   protected void deleteSemanticElement(final Operation parent, final Parameter child) {
      parent.getOwnedParameters().remove(child);
   }
}
