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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.eclipsesource.uml.glsp.core.gmodel.GModelMapper;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

public interface GModelMapperContribution {
   interface Creator extends BaseContribution {
      default Multibinder<GModelMapper<? extends EObject, ? extends GModelElement>> createGModelMapperBinding(
         final Binder binder) {
         return Multibinder.newSetBinder(binder,
            new TypeLiteral<GModelMapper<? extends EObject, ? extends GModelElement>>() {},
            idNamed());
      }

      default MapBinder<Representation, Set<GModelMapper<? extends EObject, ? extends GModelElement>>> createDiagramGModelMapperBinding(
         final Binder binder) {
         return MapBinder.newMapBinder(binder, new TypeLiteral<Representation>() {},
            new TypeLiteral<Set<GModelMapper<? extends EObject, ? extends GModelElement>>>() {});
      }
   }

   interface Contributor extends Creator, BaseRepresentationContribution {
      default void contributeGModelMapper(final Binder binder) {
         contributeGModelMapper(createGModelMapperBinding(binder));

         createDiagramGModelMapperBinding(binder).addBinding(representation())
            .to(Key.get(new TypeLiteral<Set<GModelMapper<? extends EObject, ? extends GModelElement>>>() {},
               idNamed()));
      }

      void contributeGModelMapper(Multibinder<GModelMapper<? extends EObject, ? extends GModelElement>> multibinder);
   }
}
