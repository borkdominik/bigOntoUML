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

public class UmlClass_Package {
   public static String typeId() {
      return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.NODE,
         org.eclipse.uml2.uml.Package.class.getSimpleName());
   }

   public enum Property {
      NAME,
      URI,
      VISIBILITY_KIND,
      PACKAGE_IMPORTS,
      PACKAGE_MERGES;
   }

   public static class DiagramConfiguration implements DiagramElementConfiguration.Node {

      @Override
      public Map<String, EClass> getTypeMappings() { return Map.of(
         typeId(), GraphPackage.Literals.GNODE); }

      @Override
      public Set<String> getGraphContainableElements() { return Set.of(typeId()); }

      @Override
      public Set<ShapeTypeHint> getShapeTypeHints() {
         return Set.of(
            new ShapeTypeHint(typeId(), true, true, true, false,
               List.of(UmlClass_Class.typeId(),
                  UmlClass_Class.Variant.abstractTypeId(), // Required because the client checks for the type id before
                                                           // create
                  UmlClass_Enumeration.typeId(),
                  UmlClass_Interface.typeId(),
                  UmlClass_DataType.typeId(),
                  UmlClass_PrimitiveType.typeId(),
                  UmlClass_Package.typeId())));
      }
   }
}
