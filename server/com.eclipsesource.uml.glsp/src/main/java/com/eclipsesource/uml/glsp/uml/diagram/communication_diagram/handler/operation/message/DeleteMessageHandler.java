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
package com.eclipsesource.uml.glsp.uml.diagram.communication_diagram.handler.operation.message;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.uml2.uml.Message;

import com.eclipsesource.uml.glsp.uml.diagram.communication_diagram.constants.CommunicationTypes;
import com.eclipsesource.uml.glsp.uml.handler.operations.delete.DeleteElementHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.communication_diagram.commands.message.RemoveMessageContribution;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public class DeleteMessageHandler extends DeleteElementHandler<Message> {

   public DeleteMessageHandler() {
      super(Representation.COMMUNICATION, CommunicationTypes.MESSAGE);
   }

   @Override
   protected CCommand delete(final Message element) {
      return RemoveMessageContribution.create(element);
   }

}