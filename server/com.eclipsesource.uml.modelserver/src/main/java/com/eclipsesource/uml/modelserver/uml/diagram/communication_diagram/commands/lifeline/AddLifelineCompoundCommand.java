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
package com.eclipsesource.uml.modelserver.uml.diagram.communication_diagram.commands.lifeline;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.impl.GPointImpl;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.emf.model.notation.Shape;
import org.eclipse.uml2.uml.Interaction;

import com.eclipsesource.uml.modelserver.shared.extension.NotationElementAccessor;
import com.eclipsesource.uml.modelserver.shared.extension.SemanticElementAccessor;
import com.eclipsesource.uml.modelserver.shared.notation.commands.UmlAddShapeCommand;

public class AddLifelineCompoundCommand extends CompoundCommand {
   private final NotationElementAccessor notationElementAccessor;

   public AddLifelineCompoundCommand(final EditingDomain domain, final URI modelUri,
      final GPoint position, final Interaction parentInteraction) {
      this.notationElementAccessor = new NotationElementAccessor(modelUri, domain);

      var command = new AddLifelineSemanticCommand(domain, modelUri, parentInteraction);
      var destination = shift(parentInteraction, position);

      this.append(command);
      this.append(new UmlAddShapeCommand(domain, modelUri, destination, GraphUtil.dimension(160, 50),
         () -> command.getNewLifeline()));
   }

   protected GPoint shift(final Interaction interaction, final GPoint mousePosition) {
      String semanticProxyUri = SemanticElementAccessor.getId(interaction);
      var interactionShape = notationElementAccessor.getElement(semanticProxyUri, Shape.class);
      var origin = interactionShape.getPosition();
      var size = interactionShape.getSize();

      var destination = new GPointImpl();
      destination.setX(mousePosition.getX() - origin.getX());
      destination.setY(mousePosition.getY() - origin.getY());

      return destination;
   }
}
