package com.eclipsesource.uml.modelserver.old.diagram.usecasediagram.usecasepackage;

public class RemovePackageCommandContribution { /*-{

    public static final String TYPE = "removePackage";

    public static CCompoundCommand create(final String semanticUri) {
        CCompoundCommand removePackageCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
        removePackageCommand.setType(TYPE);
        removePackageCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUri);
        return removePackageCommand;
    }

    @Override
    protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
        throws DecodingException {

        String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
        return new RemovePackageCompoundCommand(domain, modelUri, semanticUriFragment);
    }   */
}
