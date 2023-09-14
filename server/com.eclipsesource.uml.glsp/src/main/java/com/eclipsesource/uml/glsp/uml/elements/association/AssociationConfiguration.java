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
package com.eclipsesource.uml.glsp.uml.elements.association;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.glsp.uml.configuration.RepresentationEdgeConfiguration;
import com.eclipsesource.uml.glsp.uml.utils.QualifiedUtil;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class AssociationConfiguration extends RepresentationEdgeConfiguration<Association> {
   @Inject
   public AssociationConfiguration(@Assisted final Representation representation) {
      super(representation);
   }

   public String compositionTypeId() {
      return QualifiedUtil.templateTypeId(representation, DefaultTypes.EDGE, "composition",
         Association.class.getSimpleName());
   }

   public String aggregationTypeId() {
      return QualifiedUtil.templateTypeId(representation, DefaultTypes.EDGE, "aggregation",
         Association.class.getSimpleName());
   }

   public enum Property {
      NAME,
      VISIBILITY_KIND
   }

   @Override
   public Map<String, EClass> getTypeMappings() {
      return Map.of(
         typeId(), GraphPackage.Literals.GEDGE,
         aggregationTypeId(), GraphPackage.Literals.GEDGE,
         compositionTypeId(), GraphPackage.Literals.GEDGE);
   }

   @Override
   public Set<EdgeTypeHint> getEdgeTypeHints() {
      return Set.of(
         new EdgeTypeHint(typeId(), true, true, true,
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class, Actor.class)),
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class, UseCase.class))),

         new EdgeTypeHint(aggregationTypeId(), true, true, true,
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class)),
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class))),

         new EdgeTypeHint(compositionTypeId(), true, true, true,
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class)),
            existingConfigurationTypeIds(Set.of(org.eclipse.uml2.uml.Class.class, Interface.class)))

      );
   }
}
