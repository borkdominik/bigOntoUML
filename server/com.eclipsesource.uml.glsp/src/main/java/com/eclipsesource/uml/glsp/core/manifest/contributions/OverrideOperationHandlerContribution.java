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
package com.eclipsesource.uml.glsp.core.manifest.contributions;

import java.util.Set;

import org.eclipse.glsp.server.operations.OperationHandler;

import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

/*
 * Contributes to GLSP by replacing an operation handler provided by core for the specific diagram
 */
public interface OverrideOperationHandlerContribution extends BaseDiagramContribution {

   default void contributeOverrideOperationHandler(final Binder binder) {
      var multibinder = Multibinder.newSetBinder(binder, OperationHandler.class, namedRepresentation());

      contributeOverrideOperationHandler(multibinder);

      var mapbinder = MapBinder.newMapBinder(binder, new TypeLiteral<Representation>() {},
         new TypeLiteral<Set<OperationHandler>>() {});

      mapbinder.addBinding(representation())
         .to(Key.get(new TypeLiteral<Set<OperationHandler>>() {}, namedRepresentation()));
   }

   void contributeOverrideOperationHandler(Multibinder<OperationHandler> multibinder);
}
