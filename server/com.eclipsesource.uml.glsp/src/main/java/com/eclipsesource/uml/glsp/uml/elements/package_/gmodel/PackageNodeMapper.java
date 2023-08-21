/********************************************************************************
 * Copyright (c) 2021-2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.elements.package_.gmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.Package;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class PackageNodeMapper extends RepresentationGNodeMapper<Package, GNode>
   implements NamedElementGBuilder<Package> {

   @Inject
   public PackageNodeMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GNode map(final Package source) {
      var builder = new GNodeBuilder(configuration().typeId())
         .id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .add(buildHeader(source))
         .add(buildCompartment(source));

      applyShapeNotation(source, builder);

      return builder.build();
   }

   @Override
   public List<GModelElement> mapSiblings(final Package source) {
      var siblings = new ArrayList<GModelElement>();

      siblings.addAll(mapHandler.handle(source.getPackageImports()));
      siblings.addAll(mapHandler.handle(source.getPackageMerges()));

      return siblings;
   }

   protected GCompartment buildHeader(final Package source) {
      var builder = compartmentHeaderBuilder(source)
         .layout(GConstants.Layout.HBOX);

      builder.add(buildHeaderName(source, "--uml-package-icon"));

      return builder.build();
   }

   protected GCompartment buildCompartment(final Package source) {
      return freeformChildrenCompartmentBuilder(source)
         .addAll(source.getPackagedElements().stream()
            .map(mapHandler::handle)
            .collect(Collectors.toList()))
         .addAll(source.getPackagedElements().stream()
            .map(mapHandler::handleSiblings)
            .flatMap(Collection::stream)
            .collect(Collectors.toList()))
         .build();
   }
}
