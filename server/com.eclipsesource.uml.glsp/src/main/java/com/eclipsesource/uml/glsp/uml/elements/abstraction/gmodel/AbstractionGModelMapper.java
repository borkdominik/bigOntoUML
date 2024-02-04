/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.elements.abstraction.gmodel;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.uml2.uml.Abstraction;

import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGEdgeMapper;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class AbstractionGModelMapper extends RepresentationGEdgeMapper<Abstraction, GEdge> {

   @Inject
   public AbstractionGModelMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GEdge map(final Abstraction source) {
      return new GAbstractionBuilder<>(gmodelContext, source, configuration().typeId()).buildGModel();
   }
}