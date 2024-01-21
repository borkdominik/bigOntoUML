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
package com.eclipsesource.uml.glsp.uml.elements.generalization.gmodel;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.uml2.uml.Generalization;

import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGEdgeMapper;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class GeneralizationGModelMapper extends RepresentationGEdgeMapper<Generalization, GEdge> {

   @Inject
   public GeneralizationGModelMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GEdge map(final Generalization source) {
      return new GGeneralizationBuilder<>(gmodelContext, source, configuration().typeId()).buildGModel();
   }
}
