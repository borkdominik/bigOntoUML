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
package com.eclipsesource.uml.modelserver.uml.diagram.sequence_diagram.commands.lifeline;

import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseDeleteSemanticChildCommand;

public final class DeleteLifelineSemanticCommand extends BaseDeleteSemanticChildCommand<Interaction, Lifeline> {

   public DeleteLifelineSemanticCommand(final ModelContext context, final Lifeline semanticElement) {
      super(context, semanticElement.getInteraction(), semanticElement);
   }

   @Override
   protected void deleteSemanticElement(final Interaction parent, final Lifeline child) {
      parent.getLifelines().remove(child);
   }
}
