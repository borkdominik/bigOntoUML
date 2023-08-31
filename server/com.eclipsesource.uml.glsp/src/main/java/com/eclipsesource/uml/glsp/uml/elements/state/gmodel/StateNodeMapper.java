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
package com.eclipsesource.uml.glsp.uml.elements.state.gmodel;

import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.State;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class StateNodeMapper extends RepresentationGNodeMapper<State, GNode>
   implements NamedElementGBuilder<State> {

   @Inject
   public StateNodeMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GNode map(final State source) {
      var builder = new GNodeBuilder(configuration().typeId())
         .id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .addCssClass(CoreCSS.NO_STROKE)
         .add(buildHeader(source));

      if (!source.isSimple()) {
         builder.add(buildCompartment(source));
      }

      applyShapeNotation(source, builder);

      return builder.build();
   }

   protected GCompartment buildCompartment(final State source) {
      var compartment = freeformChildrenCompartmentBuilder(source);

      var stateElements = source.getRegions().stream()
         .map(mapHandler::handle)
         .collect(Collectors.toList());
      compartment.addAll(stateElements);

      return compartment.build();
   }

   protected GCompartment buildHeader(final State source) {
      var header = compartmentHeaderBuilder(source)
         .layout(GConstants.Layout.VBOX);

      header.add(buildIconVisibilityName(source, "--uml-state-icon"));

      return header.build();
   }

}
