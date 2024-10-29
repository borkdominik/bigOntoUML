package com.borkdominik.big.glsp.uml.uml.representation.onto_uml_class;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.features.validation.*;
import org.eclipse.glsp.server.model.GModelState;

import java.util.List;
import java.util.Optional;

public class ModelValidationHandler extends RequestMarkersHandler {

    @Inject
    protected Optional<ModelValidator> validator;

    @Inject
    protected GModelState modelState;

    @Override
    @SuppressWarnings("checkstyle:cyclomaticComplexity")
    public List<Action> executeAction(final RequestMarkersAction action) {
        var elements = modelState.getRoot().getChildren();

        List<Marker> markers = validator.get().validate(elements, action.getReason());

        return listOf(new SetMarkersAction(markers, action.getReason()));
    }
}
