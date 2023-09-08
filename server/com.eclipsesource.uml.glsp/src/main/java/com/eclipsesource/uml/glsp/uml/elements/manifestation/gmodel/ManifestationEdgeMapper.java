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
package com.eclipsesource.uml.glsp.uml.elements.manifestation.gmodel;

import java.util.List;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.impl.GEdgeBuilder;
import org.eclipse.glsp.graph.builder.impl.GEdgePlacementBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.Manifestation;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.core.constants.CoreTypes;
import com.eclipsesource.uml.glsp.core.gmodel.suffix.NameLabelSuffix;
import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGEdgeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.EdgeGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class ManifestationEdgeMapper extends RepresentationGEdgeMapper<Manifestation, GEdge>
   implements EdgeGBuilder {

   @Inject
   public ManifestationEdgeMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GEdge map(final Manifestation source) {
      var edgeSource = source.getOwner();
      var edgeSourceId = idGenerator.getOrCreateId(edgeSource);

      var edgeTarget = source.getUtilizedElement();
      var edgeTargetId = idGenerator.getOrCreateId(edgeTarget);

      var builder = new GEdgeBuilder(configuration().typeId())
         .id(idGenerator.getOrCreateId(source))
         .addCssClasses(List.of(CoreCSS.EDGE, CoreCSS.EDGE_DASHED))
         .addCssClass(CoreCSS.Marker.TENT.end())
         .sourceId(edgeSourceId)
         .targetId(edgeTargetId)
         .routerKind(GConstants.RouterKind.POLYLINE)
         .add(textEdgeBuilder(
            source,
            "<<manifest>>",
            new GEdgePlacementBuilder()
               .side(GConstants.EdgeSide.TOP)
               .position(0.5d)
               .rotate(false)
               .build())
                  .build());

      var nameLabel = createNameLabel(source.getName(),
         suffix.appendTo(NameLabelSuffix.SUFFIX, idGenerator.getOrCreateId(source)), 0.6d);
      builder.add(nameLabel);

      applyEdgeNotation(source, builder);

      return builder.build();
   }

   protected GLabel createNameLabel(final String name, final String id, final Double position) {
      var type = CoreTypes.LABEL_NAME;
      var side = GConstants.EdgeSide.TOP;
      return new GLabelBuilder(type)
         .edgePlacement(new GEdgePlacementBuilder().side(side).position(position).rotate(false).build())
         .id(id)
         .text(name)
         .build();
   }

}
