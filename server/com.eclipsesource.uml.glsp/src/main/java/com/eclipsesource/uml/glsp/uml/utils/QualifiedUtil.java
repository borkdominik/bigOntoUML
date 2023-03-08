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
package com.eclipsesource.uml.glsp.uml.utils;

import com.eclipsesource.uml.modelserver.unotation.Representation;

public class QualifiedUtil {

   public static String representationName(final Representation representation, final String name) {
      return String.format("%s__%s", representation.getLiteral().toLowerCase(), name);
   }

   public static String representationTypeId(final Representation representation, final String type, final String id) {
      return String.format("%s:%s__%s", type, representation.getLiteral().toLowerCase(), id);
   }

   public static String representationTemplateTypeId(final Representation representation, final String type,
      final String template, final String id) {
      return String.format("%s:%s__%s__%s", type, representation.getLiteral().toLowerCase(), template, id);
   }
}
