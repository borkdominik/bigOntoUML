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
package com.eclipsesource.uml.glsp.uml.diagram.sequence_diagram.handler.operation.message;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.uml2.uml.Lifeline;

import com.eclipsesource.uml.glsp.uml.diagram.sequence_diagram.diagram.UmlSequence_Message;
import com.eclipsesource.uml.glsp.uml.handler.operations.create.BaseCreateEdgeHandler;
import com.eclipsesource.uml.glsp.uml.handler.operations.create.CreateLocationAwareNodeHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.sequence_diagram.commands.message.CreateMessageContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.sequence_diagram.constants.UmlMessageSort;
import com.google.inject.Inject;

public class CreateDeleteMessageHandler
   extends BaseCreateEdgeHandler<Lifeline, Lifeline> implements CreateLocationAwareNodeHandler {

   public CreateDeleteMessageHandler() {
      super(UmlSequence_Message.Variant.deleteTypeId());
   }

   @Inject
   protected EMFIdGenerator idGenerator;

   @Override
   protected CCommand createCommand(final CreateEdgeOperation operation, final Lifeline source, final Lifeline target) {

      var sourcePosition = operation.getArgs().get("sourcePosition").split(",");
      double sourceX = Double.parseDouble(sourcePosition[0]);
      double sourceY = Double.parseDouble(sourcePosition[1]);

      var targetPosition = operation.getArgs().get("targetPosition").split(",");
      double targetX = Double.parseDouble(targetPosition[0]);
      double targetY = Double.parseDouble(targetPosition[1]); // could also set to 0 here

      var keyword = UmlMessageSort.DELETE;

      return CreateMessageContribution.create(
         source,
         target,
         relativeLocationOf(modelState, idGenerator.getOrCreateId(source), sourceX, sourceY),
         relativeLocationOf(modelState, idGenerator.getOrCreateId(target), targetX, targetY),
         keyword);

   }

}
