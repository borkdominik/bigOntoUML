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
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.manifest;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.eclipsesource.uml.glsp.core.diagram.DiagramConfiguration;
import com.eclipsesource.uml.glsp.core.features.toolpalette.DiagramPalette;
import com.eclipsesource.uml.glsp.core.gmodel.GModelMapper;
import com.eclipsesource.uml.glsp.core.handler.operation.create.DiagramCreateHandler;
import com.eclipsesource.uml.glsp.core.handler.operation.delete.DiagramDeleteHandler;
import com.eclipsesource.uml.glsp.core.handler.operation.directediting.DiagramLabelEditHandler;
import com.eclipsesource.uml.glsp.core.manifest.DiagramManifest;
import com.eclipsesource.uml.glsp.core.manifest.contributions.CreateHandlerContribution;
import com.eclipsesource.uml.glsp.core.manifest.contributions.DeleteHandlerContribution;
import com.eclipsesource.uml.glsp.core.manifest.contributions.LabelEditHandlerContribution;
import com.eclipsesource.uml.glsp.core.manifest.contributions.SuffixIdAppenderContribution;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.diagram.ClassDiagramConfiguration;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.AssociationEdgeMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.ClassNodeMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.EnumerationNodeMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.GeneralizationEdgeMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.InterfaceNodeMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.PropertyCompartmentMapper;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.HeaderOuterSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.HeaderTypeSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.LabelMultiplicitySuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertyIconSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertyLabelMultiplicitySuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertyLabelNameSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertyLabelTypeSuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.gmodel.suffix.PropertySuffix;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.CreateAggregationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.CreateAssociationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.CreateCompositionHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.DeleteAssociationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.SetAssociationEndMultiplicityHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.association.SetAssociationEndNameHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.enumeration.CreateEnumerationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.enumeration.DeleteEnumerationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.enumeration.RenameEnumerationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.generalization.CreateGeneralizationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.generalization.DeleteGeneralizationHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property.CreatePropertyHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property.DeletePropertyHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property.RenamePropertyHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property.SetPropertyMultiplicityHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.property.SetPropertyTypeHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uclass.CreateAbstractClassHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uclass.CreateClassHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uclass.DeleteClassHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uclass.RenameClassHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uinterface.CreateInterfaceHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uinterface.DeleteInterfaceHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.handler.operation.uinterface.RenameInterfaceHandler;
import com.eclipsesource.uml.glsp.uml.diagram.class_diagram.palette.ClassPalette;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.multibindings.Multibinder;

public class ClassUmlManifest extends DiagramManifest
   implements CreateHandlerContribution.Contributor,
   DeleteHandlerContribution.Contributor, LabelEditHandlerContribution.Contributor, SuffixIdAppenderContribution {

   @Override
   public String id() {
      return representation().getName();
   }

   @Override
   public Representation representation() {
      return Representation.CLASS;
   }

   @Override
   protected void configure() {
      super.configure();

      contributeCreateHandler(binder());
      contributeDeleteHandler(binder());
      contributeLabelEditHandler(binder());
      contributeSuffixIdAppenders();
   }

   public void contributeSuffixIdAppenders() {
      contributeSuffixIdAppenders(binder(), mapbinder -> {
         mapbinder.addBinding(HeaderOuterSuffix.SUFFIX).to(HeaderOuterSuffix.class);
         mapbinder.addBinding(HeaderTypeSuffix.SUFFIX).to(HeaderTypeSuffix.class);
         mapbinder.addBinding(PropertyIconSuffix.SUFFIX).to(PropertyIconSuffix.class);
         mapbinder.addBinding(PropertyLabelMultiplicitySuffix.SUFFIX)
            .to(PropertyLabelMultiplicitySuffix.class);
         mapbinder.addBinding(PropertyLabelNameSuffix.SUFFIX).to(PropertyLabelNameSuffix.class);
         mapbinder.addBinding(PropertyLabelTypeSuffix.SUFFIX).to(PropertyLabelTypeSuffix.class);
         mapbinder.addBinding(PropertySuffix.SUFFIX).to(PropertySuffix.class);
         mapbinder.addBinding(LabelMultiplicitySuffix.SUFFIX).to(LabelMultiplicitySuffix.class);
      });
   }

   @Override
   public void contributePalette(final Multibinder<DiagramPalette> multibinder) {
      multibinder.addBinding().to(ClassPalette.class);
   }

   @Override
   public Class<? extends DiagramConfiguration> contributeDiagramConfiguration() {
      return ClassDiagramConfiguration.class;
   }

   @Override
   public void contributeCreateHandler(final Multibinder<DiagramCreateHandler> multibinder) {
      multibinder.addBinding().to(CreateAggregationHandler.class);
      multibinder.addBinding().to(CreateAssociationHandler.class);
      multibinder.addBinding().to(CreateCompositionHandler.class);
      multibinder.addBinding().to(CreateEnumerationHandler.class);
      multibinder.addBinding().to(CreateGeneralizationHandler.class);
      multibinder.addBinding().to(CreatePropertyHandler.class);
      multibinder.addBinding().to(CreateAbstractClassHandler.class);
      multibinder.addBinding().to(CreateClassHandler.class);
      multibinder.addBinding().to(CreateInterfaceHandler.class);
   }

   @Override
   public void contributeDeleteHandler(final Multibinder<DiagramDeleteHandler<? extends EObject>> multibinder) {
      multibinder.addBinding().to(DeleteAssociationHandler.class);
      multibinder.addBinding().to(DeleteEnumerationHandler.class);
      multibinder.addBinding().to(DeleteGeneralizationHandler.class);
      multibinder.addBinding().to(DeletePropertyHandler.class);
      multibinder.addBinding().to(DeleteClassHandler.class);
      multibinder.addBinding().to(DeleteInterfaceHandler.class);
   }

   @Override
   public void contributeLabelEditHandler(
      final Multibinder<DiagramLabelEditHandler<? extends EObject>> multibinder) {
      multibinder.addBinding().to(SetAssociationEndMultiplicityHandler.class);
      multibinder.addBinding().to(SetAssociationEndNameHandler.class);
      multibinder.addBinding().to(RenameEnumerationHandler.class);
      multibinder.addBinding().to(RenamePropertyHandler.class);
      multibinder.addBinding().to(SetPropertyMultiplicityHandler.class);
      multibinder.addBinding().to(SetPropertyTypeHandler.class);
      multibinder.addBinding().to(RenameClassHandler.class);
      multibinder.addBinding().to(RenameInterfaceHandler.class);
   }

   @Override
   public void contributeGModelMapper(
      final Multibinder<GModelMapper<? extends EObject, ? extends GModelElement>> multibinder) {
      multibinder.addBinding().to(AssociationEdgeMapper.class);
      multibinder.addBinding().to(ClassNodeMapper.class);
      multibinder.addBinding().to(EnumerationNodeMapper.class);
      multibinder.addBinding().to(GeneralizationEdgeMapper.class);
      multibinder.addBinding().to(InterfaceNodeMapper.class);
      multibinder.addBinding().to(PropertyCompartmentMapper.class);
   }
}
