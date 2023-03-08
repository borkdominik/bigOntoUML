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
package com.eclipsesource.uml.glsp.old.diagram.statemachine_diagram.gmodel;

public class UmlStateMachineDiagramModelFactory { /*-

   public UmlStateMachineDiagramModelFactory(final UmlModelState modelState) {
      super(modelState);
   }

   @Override
   public GModelElement create(final EObject semanticElement) {
      GModelElement result = null;
      if (semanticElement instanceof Model) {
         result = create(semanticElement);
      } else if (semanticElement instanceof StateMachine) {
         result = stateMachineNodeFactory.create((StateMachine) semanticElement);
      } else if (semanticElement instanceof Vertex) {
         Vertex vertex = (Vertex) semanticElement;
         if (vertex instanceof Pseudostate) {
            Pseudostate pseudostate = (Pseudostate) vertex;
            if (List.of(PseudostateKind.ENTRY_POINT_LITERAL, PseudostateKind.EXIT_POINT_LITERAL)
               .contains(pseudostate.getKind())) {
               result = stateMachinePortFactory.create(pseudostate);
            } else {
               result = stateMachineDiagramVertexFactory.create((Vertex) semanticElement);
            }
         } else {
            result = stateMachineDiagramVertexFactory.create((Vertex) semanticElement);
         }
      } else if (semanticElement instanceof Region) {
         result = regionCompartmentFactory.create((Region) semanticElement);
      } else if (semanticElement instanceof Transition) {
         result = stateMachineEdgeFactory.create((Transition) semanticElement);
      }

      return result;
   }

   /*
    * @Override
    * public GGraph create(final Diagram umlDiagram) {
    * GGraph graph = getOrCreateRoot();
    * if (umlDiagram.getSemanticElement().getResolvedElement() != null) {
    * Model umlModel = (Model) umlDiagram.getSemanticElement().getResolvedElement();
    * graph.setId(toId(umlModel));
    * // just StateMachine Elements are allowed on the first level under root (Model)
    * graph.getChildren().addAll(umlModel.getPackagedElements().stream()
    * .filter(StateMachine.class::isInstance)
    * .map(StateMachine.class::cast)
    * .map(this::create)
    * .collect(Collectors.toList()));
    * }
    * return graph;
    * }
    */
}
