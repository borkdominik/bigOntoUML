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
package com.eclipsesource.uml.modelserver.shared.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;

public class ModelContext {
   public final URI uri;
   public final EditingDomain domain;

   protected ModelContext(final URI uri, final EditingDomain domain) {
      super();
      this.uri = uri;
      this.domain = domain;
   }

   public static ModelContext of(final URI uri, final EditingDomain domain) {
      return new ModelContext(uri, domain);
   }
}