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
package com.eclipsesource.uml.modelserver.shared.codec.codecs;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;

import com.eclipsesource.uml.modelserver.shared.codec.CCommandProvider;
import com.eclipsesource.uml.modelserver.shared.codec.ContextProvider;
import com.eclipsesource.uml.modelserver.shared.extension.SemanticElementAccessor;

public interface ParentCodec {
   String PARENT_SEMANTIC_ELEMENT_ID = "parent_semantic_element_id";

   interface Encoder<T> extends CCommandProvider {
      default T parent(final EObject parent) {
         ccommand().getProperties().put(ParentCodec.PARENT_SEMANTIC_ELEMENT_ID, SemanticElementAccessor.getId(parent));
         return (T) this;
      }

      default T parent(final Object parent) {
         ccommand().getProperties().put(ParentCodec.PARENT_SEMANTIC_ELEMENT_ID,
            SemanticElementAccessor.getUnsafeId(parent));
         return (T) this;
      }
   }

   interface Decoder extends CCommandProvider, ContextProvider {
      default <T> Optional<T> parent(final Class<T> clazz) {
         var parentSemanticElementId = ccommand().getProperties().get(ParentCodec.PARENT_SEMANTIC_ELEMENT_ID);
         return new SemanticElementAccessor(context()).getElement(parentSemanticElementId, clazz);
      }
   }
}
