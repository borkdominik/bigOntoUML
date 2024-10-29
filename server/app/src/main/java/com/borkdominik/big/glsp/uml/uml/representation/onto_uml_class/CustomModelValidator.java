package com.borkdominik.big.glsp.uml.uml.representation.onto_uml_class;

import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.google.inject.Inject;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.MarkerKind;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.uml2.uml.Class;

import java.util.ArrayList;
import java.util.List;

public class CustomModelValidator implements ModelValidator {

    @Inject
    protected BGEMFModelState modelState;

    @Override
    public List<Marker> validate(List<GModelElement> elements, String reason) {
        return validate(elements.toArray(GModelElement[]::new));
    }

    @Override
    public List<Marker> validate(final GModelElement... elements) {
        var markers = new ArrayList<Marker>();
        for (GModelElement element : elements) {
            if (element instanceof GNode) {
                var semantic = modelState.getElementIndex().getSemantic(element.getId()).get();

                if (semantic instanceof Class c && c.getAppliedStereotypes().isEmpty())
                    markers.add(validateGNode((GNode) element));
            }
            element.getChildren().forEach(child -> markers.addAll(validate(child)));
        }
        return markers;
    }

    protected Marker validateGNode(final GNode element) {
        return new Marker("Stereotype", "This class has no Stereotype", element.getId(),
                MarkerKind.WARNING);
    }

}