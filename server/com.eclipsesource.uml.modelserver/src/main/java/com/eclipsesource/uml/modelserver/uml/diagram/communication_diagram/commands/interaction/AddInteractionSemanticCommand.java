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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.UMLFactory;

import com.eclipsesource.uml.modelserver.shared.semantic.UmlSemanticElementCommand;
import com.eclipsesource.uml.modelserver.uml.generator.NameGenerator;
import com.eclipsesource.uml.modelserver.uml.generator.PackageableElementNameGenerator;

public class AddInteractionSemanticCommand extends UmlSemanticElementCommand {

   protected final Interaction newInteraction;
   protected final NameGenerator nameGenerator;

   public AddInteractionSemanticCommand(final EditingDomain domain, final URI modelUri) {
      super(domain, modelUri);
      this.newInteraction = UMLFactory.eINSTANCE.createInteraction();
      this.nameGenerator = new PackageableElementNameGenerator(Interaction.class, modelUri, domain);
   }

   @Override
   protected void doExecute() {
      newInteraction.setName(nameGenerator.newName());
      model.getPackagedElements().add(newInteraction);
   }

   public Interaction getNewInteraction() { return newInteraction; }

}
