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
package com.eclipsesource.uml.glsp.manifest.contributions;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.di.GLSPModule;

import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

public interface ClientActionContribution {

   default void contributeClientAction(final Binder binder) {
      var provider = Multibinder.newSetBinder(binder, Action.class, Names.named(GLSPModule.CLIENT_ACTIONS));
      contributeClientAction(provider);
   }

   void contributeClientAction(Multibinder<Action> multibinder);
}