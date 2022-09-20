package com.eclipsesource.uml.glsp.features.validation;

import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.google.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelIndex;
import org.eclipse.glsp.server.actions.AbstractActionHandler;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.RequestMarkersAction;
import org.eclipse.glsp.server.features.validation.RequestMarkersHandler;
import org.eclipse.glsp.server.features.validation.SetMarkersAction;
import org.eclipse.glsp.server.model.GModelState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UmlDiagramRequestMarkersHandler extends AbstractActionHandler<RequestMarkersAction> {

   private static final Logger LOGGER = LogManager.getLogger(RequestMarkersHandler.class);

   protected UmlDiagramModelValidator validator;

   @Inject
   protected GModelState modelState;

   @Override
   @SuppressWarnings("checkstyle:cyclomaticComplexity")
   public List<Action> executeAction(final RequestMarkersAction action) {
      UmlModelState umlModelState = UmlModelState.getModelState(modelState);
      List<String> elementsIDs = action.getElementsIDs();
      /*if (validator.isEmpty()) {
         LOGGER.warn("Cannot compute markers! No implementation for " + UmlDiagramModelValidator.class + " has been bound");
         return none();
      }*/

      // if no element ID is provided, compute the markers for the complete model
      if (elementsIDs == null || elementsIDs.size() == 0
            || (elementsIDs.size() == 1 && "EMPTY".equals(elementsIDs.get(0)))) {
         elementsIDs = Arrays.asList(umlModelState.getRoot().getId());
      }
      List<Marker> markers = new ArrayList<>();
      GModelIndex currentModelIndex = umlModelState.getIndex();
      for (String elementID : elementsIDs) {
         Optional<GModelElement> modelElement = currentModelIndex.get(elementID);
         if (modelElement.isPresent()) {
            //markers.addAll(validator.get().validate(modelElement.get()));
            markers.addAll(validator.validate(modelElement.get()));
         }

      }

      return listOf(new SetMarkersAction(markers));
   }
}
