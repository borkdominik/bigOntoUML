package com.eclipsesource.uml.modelserver.old.diagram.deploymentdiagram.deploymentedge;

public class RemoveDeploymentCommandContribution { /*-{

    public static final String TYPE = "removeDeployment";

    public static final String SOURCE_NODE_URI_FRAGMENT = "sourceNodeUriFragment";

    public static CCompoundCommand create(final String semanticUriFragment, final String semanticUriSourceFragment) {
        CCompoundCommand removeDeploymentCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
        removeDeploymentCommand.setType(TYPE);
        removeDeploymentCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUriFragment);
        removeDeploymentCommand.getProperties().put(SOURCE_NODE_URI_FRAGMENT, semanticUriSourceFragment);
        return removeDeploymentCommand;
    }

    @Override
    protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
            throws DecodingException {

        String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
        String semanticUriParentFragment = command.getProperties().get(SOURCE_NODE_URI_FRAGMENT);
        return new RemoveDeploymentCompoundCommand(domain, modelUri, semanticUriFragment, semanticUriParentFragment);
    }
       */

}
