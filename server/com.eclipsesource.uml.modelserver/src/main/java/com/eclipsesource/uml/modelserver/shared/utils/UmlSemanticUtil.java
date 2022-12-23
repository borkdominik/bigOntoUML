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
package com.eclipsesource.uml.modelserver.shared.utils;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.resource.UMLResource;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;

public final class UmlSemanticUtil {

   private UmlSemanticUtil() {}

   public static Model getModel(final ModelContext context) {
      var semanticResource = context.domain.getResourceSet()
         .getResource(context.uri.trimFileExtension().appendFileExtension(UMLResource.FILE_EXTENSION), false);
      var semanticRoot = semanticResource.getContents().get(0);

      return (Model) semanticRoot;
   }
}
