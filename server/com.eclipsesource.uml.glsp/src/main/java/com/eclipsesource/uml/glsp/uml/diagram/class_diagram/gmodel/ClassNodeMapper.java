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
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.Class;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.core.constants.CoreTypes;
import com.eclipsesource.uml.glsp.core.gmodel.suffix.NameLabelSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.constants.UmlClass_AbstractClass;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.constants.UmlClass_Class;
import com.eclipsesource.uml.glsp.uml.gmodel.BaseGNodeMapper;

public final class ClassNodeMapper extends BaseGNodeMapper<Class, GNode> {
   @Override
   public GNode map(final Class source) {
      var builder = new GNodeBuilder(UmlClass_Class.TYPE_ID);

      if (source.isAbstract()) {
         builder = new GNodeBuilder(UmlClass_AbstractClass.TYPE_ID);
      }

      builder.id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .add(buildHeader(source))
         .add(buildCompartment(source));

      applyShapeNotation(source, builder);

      return builder.build();
   }

   @Override
   public List<GModelElement> mapSiblings(final Class source) {
      return mapHandler.handle(source.getGeneralizations());
   }

   protected GCompartment buildHeader(final Class source) {
      var builder = new GCompartmentBuilder(CoreTypes.COMPARTMENT_HEADER)
         .id(idCountGenerator.getOrCreateId(source))
         .layoutOptions(new GLayoutOptions().hAlign(GConstants.HAlign.CENTER));

      if (source.isAbstract()) {
         builder
            .layout(GConstants.Layout.VBOX);

         var typeLabel = new GLabelBuilder(CoreTypes.LABEL_TEXT)
            .id(idCountGenerator.getOrCreateId(source))
            .text("{abstract}")
            .build();
         builder.add(typeLabel);

      } else {
         builder
            .layout(GConstants.Layout.HBOX);

         var icon = new GCompartmentBuilder(UmlClass_Class.ICON)
            .id(idCountGenerator.getOrCreateId(source))
            .build();
         builder.add(icon);
      }

      var nameLabel = new GLabelBuilder(CoreTypes.LABEL_NAME)
         .id(suffix.appendTo(NameLabelSuffix.SUFFIX, idGenerator.getOrCreateId(source)))
         .text(source.getName()).build();
      builder.add(nameLabel);

      return builder.build();
   }

   protected GCompartment buildCompartment(final Class source) {

      var builder = new GCompartmentBuilder(CoreTypes.COMPARTMENT)
         .id(idCountGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX);

      var layoutOptions = new GLayoutOptions()
         .hAlign(GConstants.HAlign.LEFT)
         .resizeContainer(true);
      builder.layoutOptions(layoutOptions);

      var propertyElements = source.getOwnedAttributes().stream()
         .filter(p -> p.getAssociation() == null)
         .map(mapHandler::handle)
         .collect(Collectors.toList());
      builder.addAll(propertyElements);

      var operationElements = source.getOwnedOperations().stream()
         .map(mapHandler::handle)
         .collect(Collectors.toList());
      builder.addAll(operationElements);

      return builder.build();
   }
}
