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
package com.eclipsesource.uml.glsp.uml.elements.substitution;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Substitution;

import com.eclipsesource.uml.glsp.uml.handler.element.EdgeOperationHandler;

public class SubstitutionOperationHandler extends EdgeOperationHandler<Substitution, Classifier, Classifier> {

   public SubstitutionOperationHandler() {
      super(SubstitutionConfiguration.typeId());
   }

}