package com.eclipsesource.uml.modelserver.old.diagram.deploymentdiagram.deploymentspecification;

public class AddDeploymentSpecificationCommand { /*- {

   protected final DeploymentSpecification newDeploymentSpecification;
   protected final String parentSemanticUriFragment;

   public AddDeploymentSpecificationCommand(final EditingDomain domain, final URI modelUri,
                                            final String parentSemanticUri) {
      super(domain, modelUri);
      this.newDeploymentSpecification = UMLFactory.eINSTANCE.createDeploymentSpecification();
      this.parentSemanticUriFragment = parentSemanticUri;
   }

   @Override
   protected void doExecute() {
      newDeploymentSpecification.setName(UmlSemanticCommandUtil.getNewDeploymentSpecificationName(umlModel));

      EObject parentObject = UmlSemanticCommandUtil.getElement(umlModel, parentSemanticUriFragment);
      if (parentObject instanceof Artifact) {
         Artifact parentArtifact = UmlSemanticCommandUtil.getElement(umlModel, parentSemanticUriFragment,
               Artifact.class);
         parentArtifact.getNestedArtifacts().add(newDeploymentSpecification);
      } else if (parentObject instanceof Device) {
         Device parentDevice = UmlSemanticCommandUtil.getElement(umlModel, parentSemanticUriFragment, Device.class);
         parentDevice.getNestedClassifiers().add(newDeploymentSpecification);
      } else if (parentObject instanceof ExecutionEnvironment) {
         ExecutionEnvironment parentExecutionEnvironment = UmlSemanticCommandUtil.getElement(umlModel,
               parentSemanticUriFragment, ExecutionEnvironment.class);
         parentExecutionEnvironment.getNestedClassifiers().add(newDeploymentSpecification);
      } else if (parentObject instanceof Node) {
         Node parentNode = UmlSemanticCommandUtil.getElement(umlModel, parentSemanticUriFragment, Node.class);
         parentNode.getNestedClassifiers().add(newDeploymentSpecification);
      } else {
         umlModel.getPackagedElements().add(newDeploymentSpecification);
      }
   }

   public DeploymentSpecification getNewDeploymentSpecification() {
      return newDeploymentSpecification;
   }
      */
}
