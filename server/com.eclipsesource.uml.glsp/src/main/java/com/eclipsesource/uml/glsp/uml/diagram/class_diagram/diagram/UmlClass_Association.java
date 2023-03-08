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
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.uml2.uml.Association;

import com.eclipsesource.uml.glsp.core.diagram.DiagramElementConfiguration;
import com.eclipsesource.uml.glsp.uml.utils.QualifiedUtil;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public class UmlClass_Association {
   public static String typeId() {
      return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.EDGE,
         Association.class.getSimpleName());
   }

   public static class Variant {
      public static String compositionTypeId() {
         return QualifiedUtil.representationTemplateTypeId(Representation.CLASS, DefaultTypes.EDGE, "composition",
            Association.class.getSimpleName());
      }

      public static String aggregationTypeId() {
         return QualifiedUtil.representationTemplateTypeId(Representation.CLASS, DefaultTypes.EDGE, "aggregation",
            Association.class.getSimpleName());
      }
   }

   public enum Property {
      NAME,
      VISIBILITY_KIND
   }

   public static class DiagramConfiguration implements DiagramElementConfiguration.Edge {

      @Override
      public Map<String, EClass> getTypeMappings() {
         return Map.of(
            typeId(), GraphPackage.Literals.GEDGE,
            Variant.aggregationTypeId(), GraphPackage.Literals.GEDGE, // Required because the client shows a preview
                                                                      // version
            Variant.compositionTypeId(), GraphPackage.Literals.GEDGE);
      }

      @Override
      public Set<EdgeTypeHint> getEdgeTypeHints() {
         return Set.of(
            new EdgeTypeHint(typeId(), true, true, true,
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId()),
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId())),
            new EdgeTypeHint(Variant.aggregationTypeId(), true, true, true, // Required because the client needs to
                                                                            // differentiate between the variants
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId()),
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId())),
            new EdgeTypeHint(Variant.compositionTypeId(), true, true, true,
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId()),
               List.of(UmlClass_Class.typeId(), UmlClass_Interface.typeId())));
      }
   }
}
