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
package com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.commands.uclass;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;
import com.eclipsesource.uml.modelserver.uml.generator.ListNameGenerator;

public final class CreateClassSemanticCommand extends BaseCreateSemanticChildCommand<Package, Class> {

   protected final Boolean isAbstract;

   public CreateClassSemanticCommand(final ModelContext context, final Package parent,
      final Boolean isAbstract) {
      super(context, parent);
      this.isAbstract = isAbstract;
   }

   @Override
   protected Class createSemanticElement(final Package parent) {
      var nameGenerator = new ListNameGenerator(Class.class, parent.getPackagedElements());

      return parent.createOwnedClass(nameGenerator.newName(), isAbstract);
   }

}
