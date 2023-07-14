/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.diagram.package_diagram.handler.operation.dependency;

import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.uml2.uml.Dependency;

import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.uml.diagram.package_diagram.diagram.UmlPackage_Dependency;
import com.eclipsesource.uml.glsp.uml.handler.operations.update.BaseUpdateElementHandler;
import com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.commands.dependency.UpdateDependencyArgument;
import com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.commands.dependency.UpdateDependencyContribution;

public final class UpdateDependencyHandler
   extends BaseUpdateElementHandler<Dependency, UpdateDependencyArgument> {

   public UpdateDependencyHandler() {
      super(UmlPackage_Dependency.typeId());
   }

   @Override
   protected CCommand createCommand(final UpdateOperation operation, final Dependency element,
      final UpdateDependencyArgument updateArgument) {
      return UpdateDependencyContribution.create(element, updateArgument);
   }
}
