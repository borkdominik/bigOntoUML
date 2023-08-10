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
package com.eclipsesource.uml.modelserver.uml.elements.usecase.commands;

import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;
import com.eclipsesource.uml.modelserver.uml.generator.ListNameGenerator;

public class CreateUseCaseSemanticCommand extends BaseCreateSemanticChildCommand<Component, UseCase> {

   public CreateUseCaseSemanticCommand(final ModelContext context, final Component parent) {
      super(context, parent);
   }

   @Override
   protected UseCase createSemanticElement(final Component parent) {
      var nameGenerator = new ListNameGenerator(UseCase.class, parent.getOwnedUseCases());

      var result = parent.createOwnedUseCase(nameGenerator.newName());
      result.getSubjects().add(parent);
      return result;
   }
}
