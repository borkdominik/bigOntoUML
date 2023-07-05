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
package com.eclipsesource.uml.modelserver.uml.diagram.usecase_diagram.commands.include;

import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseDeleteSemanticChildCommand;

public class DeleteIncludeSemanticCommand extends BaseDeleteSemanticChildCommand<UseCase, Include> {

   public DeleteIncludeSemanticCommand(final ModelContext context, final Include semanticElement) {
      super(context, semanticElement.getIncludingCase(), semanticElement);
   }

   @Override
   protected void deleteSemanticElement(final UseCase parent, final Include child) {
      parent.getIncludes().remove(child);
   }
}
