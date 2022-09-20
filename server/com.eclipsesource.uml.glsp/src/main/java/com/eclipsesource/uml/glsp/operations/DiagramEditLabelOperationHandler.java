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
package com.eclipsesource.uml.glsp.operations;

import org.eclipse.glsp.server.features.directediting.ApplyLabelEditOperation;

import com.eclipsesource.uml.glsp.modelserver.UmlModelServerAccess;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public interface DiagramEditLabelOperationHandler {
   boolean supports(Representation representation);

   void edit(final ApplyLabelEditOperation editLabelOperation, UmlModelServerAccess modelAccess);
}
