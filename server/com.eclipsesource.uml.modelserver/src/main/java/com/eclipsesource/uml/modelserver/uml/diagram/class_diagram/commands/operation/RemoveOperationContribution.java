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
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.operation;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.command.CCommandFactory;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;
import org.eclipse.emfcloud.modelserver.edit.command.BasicCommandContribution;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OperationOwner;

import com.eclipsesource.uml.modelserver.core.commands.noop.NoopCommand;
import com.eclipsesource.uml.modelserver.shared.constants.SemanticKeys;
import com.eclipsesource.uml.modelserver.shared.extension.SemanticElementAccessor;

public class RemoveOperationContribution extends BasicCommandContribution<Command> {

   public static final String TYPE = "class:remove_operation";

   public static CCommand create(final OperationOwner parent, final Operation operation) {
      var command = CCommandFactory.eINSTANCE.createCommand();

      command.setType(TYPE);
      command.getProperties().put(SemanticKeys.PARENT_SEMANTIC_ELEMENT_ID, SemanticElementAccessor.getUnsafeId(parent));
      command.getProperties().put(SemanticKeys.SEMANTIC_ELEMENT_ID, SemanticElementAccessor.getId(operation));

      return command;
   }

   @Override
   protected Command toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
      throws DecodingException {
      var elementAccessor = new SemanticElementAccessor(modelUri, domain);

      var parentSemanticElementId = command.getProperties().get(SemanticKeys.PARENT_SEMANTIC_ELEMENT_ID);
      var semanticElementId = command.getProperties().get(SemanticKeys.SEMANTIC_ELEMENT_ID);

      var parent = elementAccessor.getElement(parentSemanticElementId, OperationOwner.class);
      var operation = elementAccessor.getElement(semanticElementId, Operation.class);

      if (parent.isPresent() && operation.isPresent()) {
         return new RemoveOperationSemanticCommand(domain, modelUri, parent.get(), operation.get());
      }

      return new NoopCommand();
   }

}