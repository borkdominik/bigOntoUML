package com.eclipsesource.uml.modelserver.old.diagram.statemachinediagram.transition.specific;

public class AddTransitionTriggerCommandContribution { /*-{

    public static final String TYPE = "addTransitionTrigger";
    public static final String NEW_VALUE = "newValue";

    public static CCommand create(final String semanticUri, final String newValue) {
        CCommand setEffectCommand = CCommandFactory.eINSTANCE.createCommand();
        setEffectCommand.setType(TYPE);
        setEffectCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUri);
        setEffectCommand.getProperties().put(NEW_VALUE, newValue);
        return setEffectCommand;
    }

    @Override
    protected Command toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
            throws DecodingException {
        String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
        String newValue = command.getProperties().get(NEW_VALUE);
        return new AddTransitionTriggerCommand(domain, modelUri, semanticUriFragment, newValue);
    }   */
}
