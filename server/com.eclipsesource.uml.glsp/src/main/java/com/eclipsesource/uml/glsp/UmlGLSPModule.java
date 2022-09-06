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
package com.eclipsesource.uml.glsp;

import com.eclipsesource.uml.glsp.actions.ReturnTypesAction;
import com.eclipsesource.uml.glsp.actions.UmlGetTypesActionHandler;
import com.eclipsesource.uml.glsp.contextmenu.UmlContextMenuItemProvider;
import com.eclipsesource.uml.glsp.diagram.UmlDiagramConfiguration;
import com.eclipsesource.uml.glsp.layout.UmlLayoutEngine;
import com.eclipsesource.uml.glsp.model.UmlModelFactory;
import com.eclipsesource.uml.glsp.model.UmlModelSourceLoader;
import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.operations.*;
import com.eclipsesource.uml.glsp.operations.common.CreateCommentNodeOperationHandler;
import com.eclipsesource.uml.glsp.palette.UmlToolPaletteItemProvider;
import com.eclipsesource.uml.glsp.uml.activity_diagram.actions.behavior.CallBehaviorsAction;
import com.eclipsesource.uml.glsp.uml.activity_diagram.actions.edgelabels.CreateGuardActionHandler;
import com.eclipsesource.uml.glsp.uml.activity_diagram.actions.edgelabels.CreateWeightActionHandler;
import com.eclipsesource.uml.glsp.uml.activity_diagram.operations.CreateActivityDiagramChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.activity_diagram.operations.CreateActivityDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.activity_diagram.operations.CreateActivityDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.class_diagram.operations.CreateClassDiagramChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.class_diagram.operations.CreateClassDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.class_diagram.operations.CreateClassDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.deployment_diagram.operations.CreateDeploymentDiagramChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.deployment_diagram.operations.CreateDeploymentDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.deployment_diagram.operations.CreateDeploymentDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.object_diagram.operations.CreateObjectDiagramChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.object_diagram.operations.CreateObjectDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.object_diagram.operations.CreateObjectDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.actions.AddTransitionEffectActionHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.actions.AddTransitionGuardActionHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.actions.AddTransitionLabelActionHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.actions.AddTransitionTriggerActionHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.operations.CreateStateMachineDiagramChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.operations.CreateStateMachineDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.operations.CreateStateMachineDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.statemachine_diagram.operations.CreateStateMachineDiagramStateChildNodeOperationHandler;
import com.eclipsesource.uml.glsp.uml.usecase_diagram.operations.CreateUseCaseDiagramEdgeOperationHandler;
import com.eclipsesource.uml.glsp.uml.usecase_diagram.operations.CreateUseCaseDiagramNodeOperationHandler;
import com.eclipsesource.uml.glsp.validator.UmlDiagramModelValidator;
import org.eclipse.emfcloud.modelserver.glsp.EMSGLSPModule;
import org.eclipse.emfcloud.modelserver.glsp.model.EMSModelState;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.contextmenu.ContextMenuItemProvider;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.directediting.ApplyLabelEditOperationHandler;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.layout.LayoutEngine;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.eclipse.glsp.server.operations.OperationHandlerRegistry;
import org.eclipse.glsp.server.operations.gmodel.*;


public class UmlGLSPModule extends EMSGLSPModule {

   @Override
   protected void configureActionHandlers(final MultiBinding<ActionHandler> bindings) {
      super.configureActionHandlers(bindings);
      bindings.add(UmlGetTypesActionHandler.class);
      bindings.add(CreateGuardActionHandler.class);
      bindings.add(CreateWeightActionHandler.class);
      bindings.add(AddTransitionEffectActionHandler.class);
      bindings.add(AddTransitionGuardActionHandler.class);
      bindings.add(AddTransitionLabelActionHandler.class);
      bindings.add(AddTransitionTriggerActionHandler.class);
      //bindings.add(UmlDiagramRequestMarkersHandler.class);
   }

   @Override
   protected Class<? extends OperationHandlerRegistry> bindOperationHandlerRegistry() {
      return UmlDIOperationHandlerRegistry.class;
   }

   @Override
   protected Class<? extends EMSModelState> bindGModelState() {
      return UmlModelState.class;
   }

   @Override
   public Class<? extends GModelFactory> bindGModelFactory() {
      return UmlModelFactory.class;
   }

   @Override
   protected Class<? extends ModelSourceLoader> bindSourceModelLoader() {
      return UmlModelSourceLoader.class;
   }

   @Override
   protected Class<? extends LayoutEngine> bindLayoutEngine() {
      return UmlLayoutEngine.class;
   }

   @Override
   protected Class<? extends ToolPaletteItemProvider> bindToolPaletteItemProvider() {
      return UmlToolPaletteItemProvider.class;
   }

   @Override
   protected void configureClientActions(final MultiBinding<Action> bindings) {
      super.configureClientActions(bindings);
      bindings.add(ReturnTypesAction.class);
      bindings.add(CallBehaviorsAction.class);
   }

   @Override
   protected void configureOperationHandlers(final MultiBinding<OperationHandler> bindings) {
      super.configureOperationHandlers(bindings);
      bindings.rebind(CompoundOperationHandler.class, UmlCompoundOperationHandler.class);
      bindings.rebind(ApplyLabelEditOperationHandler.class, UmlLabelEditOperationHandler.class);
      bindings.rebind(ChangeBoundsOperationHandler.class, UmlChangeBoundsOperationHandler.class);
      bindings.rebind(ChangeRoutingPointsHandler.class, UmlChangeRoutingPointsOperationHandler.class);
      bindings.rebind(DeleteOperationHandler.class, UmlDeleteOperationHandler.class);
      bindings.add(LayoutOperationHandler.class);

      // CLASS
      bindings.add(CreateClassDiagramNodeOperationHandler.class);
      bindings.add(CreateClassDiagramChildNodeOperationHandler.class);
      bindings.add(CreateClassDiagramEdgeOperationHandler.class);
      // OBJECT
      bindings.add(CreateObjectDiagramNodeOperationHandler.class);
      bindings.add(CreateObjectDiagramChildNodeOperationHandler.class);
      bindings.add(CreateObjectDiagramEdgeOperationHandler.class);
      // ACTIVITY
      bindings.add(CreateActivityDiagramNodeOperationHandler.class);
      bindings.add(CreateActivityDiagramChildNodeOperationHandler.class);
      bindings.add(CreateActivityDiagramEdgeOperationHandler.class);
      bindings.add(CreateCommentNodeOperationHandler.class);
      // USECASE
      bindings.add(CreateUseCaseDiagramNodeOperationHandler.class);
      bindings.add(CreateUseCaseDiagramEdgeOperationHandler.class);
      // DEPLOYMENT
      bindings.add(CreateDeploymentDiagramNodeOperationHandler.class);
      bindings.add(CreateDeploymentDiagramChildNodeOperationHandler.class);
      bindings.add(CreateDeploymentDiagramEdgeOperationHandler.class);
      // STATEMACHINE
      bindings.add(CreateStateMachineDiagramNodeOperationHandler.class);
      bindings.add(CreateStateMachineDiagramEdgeOperationHandler.class);
      bindings.add(CreateStateMachineDiagramChildNodeOperationHandler.class);
      bindings.add(CreateStateMachineDiagramStateChildNodeOperationHandler.class);
   }

   @Override
   protected Class<? extends DiagramConfiguration> bindDiagramConfiguration() {
      return UmlDiagramConfiguration.class;
   }

   @Override
   protected Class<? extends ModelValidator> bindModelValidator() {
      return UmlDiagramModelValidator.class;
   }

   @Override
   protected Class<? extends ContextMenuItemProvider> bindContextMenuItemProvider() {
      return UmlContextMenuItemProvider.class;
   }

   @Override
   public String getDiagramType() {
      return "umldiagram";
   }
}
