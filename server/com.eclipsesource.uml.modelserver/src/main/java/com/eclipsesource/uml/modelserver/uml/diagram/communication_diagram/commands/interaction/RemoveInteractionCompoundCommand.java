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
package com.eclipsesource.uml.modelserver.uml.diagram.communication_diagram.commands.interaction;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Interaction;

import com.eclipsesource.uml.modelserver.shared.notation.commands.UmlRemoveNotationElementCommand;
import com.eclipsesource.uml.modelserver.uml.diagram.communication_diagram.matcher.CommunicationDiagramCrossReferenceRemover;

public class RemoveInteractionCompoundCommand extends CompoundCommand {

   public RemoveInteractionCompoundCommand(final EditingDomain domain, final URI modelUri,
      final Interaction interaction) {
      this.append(new RemoveInteractionSemanticCommand(domain, modelUri, interaction));
      this.append(new UmlRemoveNotationElementCommand(domain, modelUri, interaction));

      new CommunicationDiagramCrossReferenceRemover(domain, modelUri).removeCommandsFor(interaction)
         .forEach(this::append);
   }
}
