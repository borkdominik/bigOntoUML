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
package com.eclipsesource.uml.glsp.uml.elements.actor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.types.ShapeTypeHint;
import org.eclipse.uml2.uml.Actor;

import com.eclipsesource.uml.glsp.uml.configuration.RepresentationNodeConfiguration;
import com.eclipsesource.uml.glsp.uml.utils.QualifiedUtil;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ActorConfiguration extends RepresentationNodeConfiguration<Actor> {

   @Inject
   public ActorConfiguration(@Assisted final Representation representation) {
      super(representation);
   }

   public String stickFigureNodeTypeId() {
      return QualifiedUtil.representationTemplateTypeId(representation, DefaultTypes.NODE,
         "GModel",
         "STICKFIGURE");
   }

   public enum Property {
      NAME,
      IS_ABSTRACT
   }

   @Override
   public Map<String, EClass> getTypeMappings() { return Map.of(
      typeId(), GraphPackage.Literals.GNODE); }

   @Override
   public Set<String> getGraphContainableElements() { return Set.of(typeId()); }

   @Override
   public Set<ShapeTypeHint> getShapeTypeHints() {
      return Set.of(
         new ShapeTypeHint(typeId(), true, true, true, false, List.of()));
   }
}