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
package com.eclipsesource.uml.glsp.uml.elements.enumeration_literal;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.eclipsesource.uml.glsp.core.diagram.DiagramElementConfiguration.Node;
import com.eclipsesource.uml.glsp.core.features.label_edit.DiagramLabelEditMapper;
import com.eclipsesource.uml.glsp.core.gmodel.GModelMapper;
import com.eclipsesource.uml.glsp.core.manifest.DiagramManifest;
import com.eclipsesource.uml.glsp.features.property_palette.mapper.DiagramElementPropertyMapper;
import com.eclipsesource.uml.glsp.uml.elements.enumeration_literal.features.EnumerationLiteralLabelEditMapper;
import com.eclipsesource.uml.glsp.uml.elements.enumeration_literal.features.EnumerationLiteralPropertyMapper;
import com.eclipsesource.uml.glsp.uml.elements.enumeration_literal.gmodel.EnumerationLiteralCompartmentMapper;
import com.eclipsesource.uml.glsp.uml.handler.element.NodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.manifest.node.NodeOperationHandlerDefinition;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class EnumerationLiteralDefinitionModule extends NodeOperationHandlerDefinition {

   public EnumerationLiteralDefinitionModule(final DiagramManifest manifest) {
      super(manifest.id(), manifest.representation());
   }

   @Override
   protected void diagramConfigurations(final Multibinder<Node> contribution) {
      contribution.addBinding().to(EnumerationLiteralConfiguration.Diagram.class);
   }

   @Override
   protected Optional<TypeLiteral<? extends NodeOperationHandler<?, ?>>> operationHandler() {
      return Optional.of(new TypeLiteral<EnumerationLiteralOperationHandler>() {});
   }

   @Override
   protected void diagramLabelEditMappers(final Multibinder<DiagramLabelEditMapper<? extends EObject>> contribution) {
      contribution.addBinding().to(EnumerationLiteralLabelEditMapper.class);
   }

   @Override
   protected void diagramPropertyPaletteMappers(
      final Multibinder<DiagramElementPropertyMapper<? extends EObject>> contribution) {
      contribution.addBinding().to(EnumerationLiteralPropertyMapper.class);
   }

   @Override
   protected void gmodelMappers(
      final Multibinder<GModelMapper<? extends EObject, ? extends GModelElement>> contribution) {
      contribution.addBinding().to(EnumerationLiteralCompartmentMapper.class);
   }
}