/********************************************************************************
 * Copyright (c) 2024 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.uml.uml;

import java.lang.Class;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.*;

import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;

public enum UMLTypes implements BGTypeProvider {
   ABSTRACT_CLASS("AbstractClass"),
   ABSTRACTION(List.of(Abstraction.class)),
   ACCEPT_EVENT_ACTION(List.of(AcceptEventAction.class)),
   ACTIVITY(List.of(Activity.class)),
   ACTIVITY_FINAL_NODE(List.of(ActivityFinalNode.class)),
   ACTIVITY_NODE(List.of(ActivityNode.class)),
   ACTIVITY_PARAMETER_NODE(List.of(ActivityParameterNode.class)),
   ACTIVITY_PARTITION(List.of(ActivityPartition.class)),
   ACTOR(List.of(Actor.class)),
   ACTOR_STICKFIGURE("ActorStickfigure"),
   AGGREGATION("Aggregation"),
   ARTIFACT(List.of(Artifact.class)),
   ASSOCIATION(List.of(Association.class)),
   CENTRAL_BUFFER_NODE(List.of(CentralBufferNode.class)),
   CHOICE("Choice"),
   CLASS(List.of(org.eclipse.uml2.uml.Class.class)),
   COMMUNICATION_PATH(List.of(CommunicationPath.class)),
   COMPONENT(List.of(Component.class)),
   COMPOSITION("Composition"),
   CONTROL_FLOW(List.of(ControlFlow.class)),
   DATA_TYPE(List.of(DataType.class)),
   DECISION_NODE(List.of(DecisionNode.class)),
   DEEP_HISTORY("DeepHistory"),
   DEPENDENCY(List.of(Dependency.class)),
   DEPLOYMENT(List.of(Deployment.class)),
   DEPLOYMENT_SPECIFICATION(List.of(DeploymentSpecification.class)),
   DEVICE(List.of(Device.class)),
   ELEMENT_IMPORT(List.of(ElementImport.class)),
   ENUMERATION(List.of(Enumeration.class)),
   ENUMERATION_LITERAL(List.of(EnumerationLiteral.class)),
   EXECUTION_ENVIRONMENT(List.of(ExecutionEnvironment.class)),
   EXTEND(List.of(Extend.class)),
   FINAL_STATE(List.of(FinalState.class)),
   FORK("Fork"),
   FORK_NODE(List.of(ForkNode.class)),
   FLOW_FINAL_NODE(List.of(FlowFinalNode.class)),
   GENERALIZATION(List.of(Generalization.class)),
   INCLUDE(List.of(Include.class)),
   INFORMATION_FLOW(List.of(InformationFlow.class)),
   INITIAL_NODE(List.of(InitialNode.class)),
   INITIAL_STATE("InitialState"),
   INPUT_PIN(List.of(InputPin.class)),
   INSTANCE_SPECIFICATION(List.of(InstanceSpecification.class)),
   INTERACTION(List.of(Interaction.class)),
   INTERFACE(List.of(Interface.class)),
   INTERFACE_REALIZATION(List.of(InterfaceRealization.class)),
   JOIN("Join"),
   JOIN_NODE(List.of(JoinNode.class)),
   LIFELINE(List.of(Lifeline.class)),
   LITERAL_BOOLEAN(List.of(LiteralBoolean.class)),
   LITERAL_INTEGER(List.of(LiteralInteger.class)),
   LITERAL_SPECIFICATION(List.of(LiteralSpecification.class)),
   LITERAL_STRING(List.of(LiteralString.class)),
   MANIFESTATION(List.of(Manifestation.class)),
   MERGE_NODE(List.of(MergeNode.class)),
   MESSAGE(List.of(Message.class)),
   MODEL(List.of(Model.class)),
   NODE(List.of(Node.class)),
   OPAQUE_ACTION(List.of(OpaqueAction.class)),
   OPERATION(List.of(Operation.class)),
   OUTPUT_PIN(List.of(OutputPin.class)),
   PACKAGE(List.of(org.eclipse.uml2.uml.Package.class)),
   PACKAGE_IMPORT(List.of(PackageImport.class)),
   PACKAGE_MERGE(List.of(PackageMerge.class)),
   PARAMETER(List.of(Parameter.class)),
   PIN(List.of(Pin.class)),
   PRIMITIVE_TYPE(List.of(PrimitiveType.class)),
   PROPERTY(List.of(Property.class)),
   PROPERTY_MULTIPLICITY("PropertyMultiplicity"),
   PROPERTY_TYPE("PropertyType"),
   PSEUDOSTATE(List.of(Pseudostate.class)),
   REALIZATION(List.of(Realization.class)),
   REGION(List.of(Region.class)),
   SEND_SIGNAL_ACTION(List.of(SendSignalAction.class)),
   SHALLOW_HISTORY("ShallowHistory"),
   SLOT(List.of(Slot.class)),
   STATE(List.of(State.class)),
   STATE_MACHINE(List.of(StateMachine.class)),
   SUBSTITUTION(List.of(Substitution.class)),
   TRANSITION(List.of(Transition.class)),
   USAGE(List.of(Usage.class)),
   STEREOTYPE(List.of(Stereotype.class)),
   USE_CASE(List.of(UseCase.class));

   private final String typeId;
   private final Collection<Class<? extends EObject>> handledElements;

   UMLTypes(final String typeId) {
      this(typeId, List.of());
   }

   UMLTypes(final List<Class<? extends EObject>> handledElements) {
      this(handledElements.get(0).getSimpleName(), handledElements);
   }

   UMLTypes(final String typeId, final List<Class<? extends EObject>> handledElements) {
      this.typeId = typeId;
      this.handledElements = handledElements;
   }

   @Override
   public String typeId() {
      return typeId;
   }

   @Override
   public Collection<Class<? extends EObject>> handledElements() {
      return handledElements;
   }

}
