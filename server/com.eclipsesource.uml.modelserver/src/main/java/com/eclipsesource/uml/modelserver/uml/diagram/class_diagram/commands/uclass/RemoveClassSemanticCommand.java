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
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uclass;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Class;

import com.eclipsesource.uml.modelserver.shared.semantic.UmlSemanticElementCommand;

public class RemoveClassSemanticCommand extends UmlSemanticElementCommand {

   protected final Class classToRemove;

   public RemoveClassSemanticCommand(final EditingDomain domain, final URI modelUri, final Class classToRemove) {
      super(domain, modelUri);
      this.classToRemove = classToRemove;
   }

   @Override
   protected void doExecute() {
      model.getPackagedElements().remove(classToRemove);
   }
}