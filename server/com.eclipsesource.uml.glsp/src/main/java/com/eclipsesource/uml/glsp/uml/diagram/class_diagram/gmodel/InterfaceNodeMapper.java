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

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.Interface;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.core.constants.CoreTypes;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.constants.ClassTypes;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.ClassSuffix;
import com.eclipsesource.uml.glsp.uml.gmodel.BaseGNodeMapper;
import com.google.inject.Inject;

public class InterfaceNodeMapper extends BaseGNodeMapper<Interface, GNode> {
   @Inject
   private ClassSuffix classSuffix;

   @Override
   public GNode map(final Interface umlInterface) {
      var builder = new GNodeBuilder(ClassTypes.INTERFACE)
         .id(idGenerator.getOrCreateId(umlInterface))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .add(buildHeader(umlInterface));

      applyShapeNotation(umlInterface, builder);

      return builder.build();
   }

   protected GCompartment buildHeader(final Interface umlInterface) {
      var builder = new GCompartmentBuilder(CoreTypes.COMPARTMENT_HEADER)
         .layout(GConstants.Layout.VBOX)
         .id(suffix.headerSuffix.appendTo(idGenerator.getOrCreateId(umlInterface)));

      var typeLabel = new GLabelBuilder(CoreTypes.LABEL_TEXT)
         .id(classSuffix.headerTypeSuffix.appendTo(idGenerator.getOrCreateId(umlInterface)))
         .text("«interface»")
         .build();
      builder.add(typeLabel);

      var nameLabel = new GLabelBuilder(CoreTypes.LABEL_NAME)
         .id(suffix.headerLabelSuffix.appendTo(idGenerator.getOrCreateId(umlInterface)))
         .text(umlInterface.getName())
         .build();
      builder.add(nameLabel);

      return builder.build();
   }
}