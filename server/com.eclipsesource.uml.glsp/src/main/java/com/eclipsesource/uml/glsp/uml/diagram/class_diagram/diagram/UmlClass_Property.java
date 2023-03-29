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
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.diagram;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.types.ShapeTypeHint;

import com.eclipsesource.uml.glsp.core.diagram.DiagramElementConfiguration;
import com.eclipsesource.uml.glsp.uml.utils.QualifiedUtil;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public class UmlClass_Property {
   public static String ID = org.eclipse.uml2.uml.Property.class.getSimpleName();

   public static String typeId() {
      return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.COMPARTMENT,
         ID);
   }

   public static class Label {
      public static String typeTypeId() {
         return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.LABEL,
            ID + "-type");
      }

      public static String multiplicityTypeId() {
         return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.LABEL,
            ID + "-multiplicity");
      }
   }

   public enum Property {
      NAME,
      IS_DERIVED,
      IS_ORDERED,
      IS_STATIC,
      IS_DERIVED_UNION,
      IS_READ_ONLY,
      IS_UNIQUE,
      VISIBILITY_KIND,
      MULTIPLICITY,
      IS_NAVIGABLE,
      AGGREGATION,
      TYPE;
   }

   public static class DiagramConfiguration implements DiagramElementConfiguration.Node {

      @Override
      public Map<String, EClass> getTypeMappings() {
         return Map.of(
            typeId(), GraphPackage.Literals.GCOMPARTMENT,
            Label.typeTypeId(), GraphPackage.Literals.GLABEL,
            Label.multiplicityTypeId(), GraphPackage.Literals.GLABEL);
      }

      @Override
      public Set<String> getGraphContainableElements() { return Set.of(typeId()); }

      @Override
      public Set<ShapeTypeHint> getShapeTypeHints() {
         return Set.of(
            new ShapeTypeHint(typeId(), false, true, false, false,
               List.of()));
      }
   }
}
