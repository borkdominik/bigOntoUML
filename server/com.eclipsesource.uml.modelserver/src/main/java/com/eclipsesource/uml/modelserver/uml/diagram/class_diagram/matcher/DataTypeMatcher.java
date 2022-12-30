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
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.matcher;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.uml.DataType;

public final class DataTypeMatcher {

   public static Optional<DataType> ofChildUsage(final Setting setting, final EObject interest) {
      var eObject = setting.getEObject();

      if (eObject instanceof DataType) {
         var element = (DataType) eObject;

         if (element.getPackage().equals(interest)) {
            return Optional.of(element);
         }
      }

      return Optional.empty();
   }
}
