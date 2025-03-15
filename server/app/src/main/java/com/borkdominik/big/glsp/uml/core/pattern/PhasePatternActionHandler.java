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
package com.borkdominik.big.glsp.uml.core.pattern;

import com.borkdominik.big.glsp.server.core.handler.action.integrations.BGEMFActionHandler;
import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.google.inject.Inject;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.NotationElement;
import org.eclipse.glsp.server.emf.model.notation.NotationFactory;
import org.eclipse.glsp.server.emf.model.notation.Shape;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;

import java.util.Collections;
import java.util.List;

public class PhasePatternActionHandler extends BGEMFActionHandler<PhasePatternAction> {

    @Inject
    protected BGEMFModelState modelState;
    @Inject
    protected EMFIdGenerator idGenerator;

    @Override
    protected List<Action> executeAction(final PhasePatternAction actualAction) {
        var model = ((Model) modelState.getSemanticModel());
        var notationalModel = modelState.getNotationModel();

        var idProvider = createClass(actualAction.getOptions().get("idPorvider"), "kind", model);
        model.getPackagedElements().add(idProvider);
        notationalModel.getElements().add(createShape(idProvider, 0, 0));

        var counter = 0;
        for (String s : actualAction.getPhases()) {
            var umlClass = createClass(s, "phase", model);
            var generalisation = umlClass.createGeneralization(idProvider);

            notationalModel.getElements().add(createShape(umlClass, counter * 200, 100));
            createEdge(generalisation);
            counter++;
        }

        return Collections.emptyList();
    }

    private Class createClass(String className, String stereotype, Model model) {
        Class uml_class = UMLFactory.eINSTANCE.createClass();

        uml_class.setName(className);
        model.getPackagedElements().add(uml_class);
        uml_class.getApplicableStereotypes().stream()
                .filter(s -> s.getName().equalsIgnoreCase(stereotype))
                .findFirst()
                .ifPresent(uml_class::applyStereotype);
        return uml_class;
    }

    private Shape createShape(Class uml_class, int x, int y) {
        var elementId = idGenerator.getOrCreateId(uml_class);
        var shape = NotationFactory.eINSTANCE.createShape();
        shape.setPosition(GraphUtil.point(x, y));
        shape.setSize(GraphUtil.dimension(60, 45));

        var reference = NotationFactory.eINSTANCE.createSemanticElementReference();
        reference.setElementId(elementId);
        shape.setSemanticElement(reference);

        return shape;
    }

    private void createEdge(Generalization generalisation) {
        var edge = NotationFactory.eINSTANCE.createEdge();
        var sourceId = idGenerator.getOrCreateId(generalisation.getSpecific());
        var targetId = idGenerator.getOrCreateId(generalisation.getGeneral());
        edge.setSource(findElement(sourceId));
        edge.setTarget(findElement(targetId));

        var elementId = idGenerator.getOrCreateId(generalisation);

        var reference = NotationFactory.eINSTANCE.createSemanticElementReference();
        reference.setElementId(elementId);
        edge.setSemanticElement(reference);
        modelState.getNotationModel().getElements().add(edge);
    }

    private NotationElement findElement(String elementId) {
        return modelState.getNotationModel().getElements()
                .stream().filter(notationElement -> notationElement.getSemanticElement().getElementId().equals(elementId))
                .findFirst().get();
    }

}
