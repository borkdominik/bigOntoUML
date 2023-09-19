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
package com.eclipsesource.uml.modelserver.uml.elements.element_import.commands;

import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticRelationCommand;

public class CreateElementImportSemanticCommand
   extends BaseCreateSemanticRelationCommand<ElementImport, Package, PackageableElement> {

   public CreateElementImportSemanticCommand(final ModelContext context,
      final Package source, final PackageableElement target) {
      super(context, source, target);
   }

   @Override
   protected ElementImport createSemanticElement(final Package source, final PackageableElement target) {
      return source.createElementImport(target);
   }

}
