package com.borkdominik.big.glsp.uml.core.model;

import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.NotationElement;
import org.eclipse.glsp.server.emf.model.notation.NotationFactory;
import org.eclipse.glsp.server.emf.model.notation.Shape;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OntoModelImporter {

    private final Map<String, NamedElement> objectMap = new HashMap<>();

    public void importModel(String urlPath, BGEMFModelState model, EMFIdGenerator idGenerator) {
        File file = new File(urlPath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found: " + urlPath);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(file);
            for (JsonNode uml_package : jsonNode.get("model").get("contents"))
                for (JsonNode element : uml_package.get("contents"))
                    processClasses(element, model, idGenerator);
            for (JsonNode uml_package : jsonNode.get("model").get("contents"))
                for (JsonNode element : uml_package.get("contents"))
                    processRelations(element, model, idGenerator);

            processDiagrams(jsonNode.get("diagrams"), model, idGenerator);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    private void processClasses(JsonNode jsonNode, BGEMFModelState model, EMFIdGenerator idGenerator) {
        for (JsonNode element : jsonNode.get("contents")) {
            String type = element.get("type").asText();
            if (type.equals("Class")) {
                createClass(element, model, idGenerator);
            }
        }
    }

    private void processRelations(JsonNode jsonNode, BGEMFModelState model, EMFIdGenerator idGenerator) {
        for (JsonNode element : jsonNode.get("contents")) {
            String type = element.get("type").asText();
            switch (type) {
                case "Relation":
                    createAssociation(element, model, idGenerator);
                    break;
                case "Generalization":
                    createGeneralisation(element, model, idGenerator);
                    break;
            }
        }
    }

    private void processDiagrams(JsonNode node, BGEMFModelState model, EMFIdGenerator idGenerator) {
        //TODO: add support for importing multiple diagrams from one file
        for (JsonNode element : node.get(0).get("contents")) {
            String type = element.get("type").asText();
            switch (type) {
                case "ClassView":
                    var umlClass = objectMap.get(element.get("modelElement").get("id").asText());
                    var elementId = idGenerator.getOrCreateId(umlClass);

                    var shape = ((Shape) findElement(model, elementId));
                    shape.setPosition(GraphUtil.point(element.get("shape").get("x").asInt(), element.get("shape").get("y").asInt()));
                    break;
            }
        }
    }

    private void createClass(JsonNode classNode, BGEMFModelState model, EMFIdGenerator idGenerator) {
        UMLFactory factory = UMLFactory.eINSTANCE;
        Class umlClass = factory.createClass();

        String id = classNode.get("id").asText();
        String className = classNode.get("name").asText();
        umlClass.setName(className);

        objectMap.put(id, umlClass);

        ((Model) model.getSemanticModel()).getPackagedElements().add(umlClass);

        var elementId = idGenerator.getOrCreateId(umlClass);
        var shape = NotationFactory.eINSTANCE.createShape();
        shape.setPosition(GraphUtil.point(0, 0));
        shape.setSize(GraphUtil.dimension(60, 45));

        var reference = NotationFactory.eINSTANCE.createSemanticElementReference();
        reference.setElementId(elementId);
        shape.setSemanticElement(reference);

        (model.getNotationModel()).getElements().add(shape);
        umlClass.getApplicableStereotypes().stream()
                .filter(s -> s.getName().equalsIgnoreCase(classNode.get("stereotype").asText()))
                .findFirst()
                .ifPresent(umlClass::applyStereotype);
    }

    private void createAssociation(JsonNode relationNode, BGEMFModelState fullModel, EMFIdGenerator idGenerator) {
        var model = (Model) fullModel.getSemanticModel();
        UMLFactory factory = UMLFactory.eINSTANCE;
        Association association = factory.createAssociation();
        var id = relationNode.get("id").asText();

        if (!relationNode.has("properties")) return;

        for (JsonNode propertyNode : relationNode.get("properties")) {
            if (!propertyNode.has("propertyType")) continue;

            String classId = propertyNode.get("propertyType").get("id").asText();
            Class relatedClass = (Class) objectMap.get(classId);

            if (relatedClass != null) {
                Property end = factory.createProperty();
                end.setType(relatedClass);
                association.getOwnedEnds().add(end);
            }
        }
        if (association.getMemberEnds().size() != 2)
            return;

        objectMap.put(id, association);

        model.getPackagedElements().add(association);

        var edge = NotationFactory.eINSTANCE.createEdge();
        var sourceId = idGenerator.getOrCreateId(association.getMemberEnds().get(0).getType());
        var targetId = idGenerator.getOrCreateId(association.getMemberEnds().get(1).getType());
        edge.setSource(findElement(fullModel, sourceId));
        edge.setTarget(findElement(fullModel, targetId));

        var elementId = idGenerator.getOrCreateId(association);

        var reference = NotationFactory.eINSTANCE.createSemanticElementReference();
        reference.setElementId(elementId);
        edge.setSemanticElement(reference);

        (fullModel.getNotationModel()).getElements().add(edge);

        association.getApplicableStereotypes().stream()
                .filter(s -> s.getName().equalsIgnoreCase(relationNode.get("stereotype").asText()))
                .findFirst()
                .ifPresent(association::applyStereotype);
    }

    private void createGeneralisation(JsonNode relationNode, BGEMFModelState fullModel, EMFIdGenerator idGenerator) {
        var generalId = relationNode.get("general").get("id").asText();
        var specificId = relationNode.get("specific").get("id").asText();

        var specific = (Class) objectMap.get(specificId);
        var general = (Class) objectMap.get(generalId);
        var generalisation = specific.createGeneralization(general);

        var edge = NotationFactory.eINSTANCE.createEdge();
        var sourceId = idGenerator.getOrCreateId(specific);
        var targetId = idGenerator.getOrCreateId(general);
        edge.setSource(findElement(fullModel, sourceId));
        edge.setTarget(findElement(fullModel, targetId));

        var elementId = idGenerator.getOrCreateId(generalisation);

        var reference = NotationFactory.eINSTANCE.createSemanticElementReference();
        reference.setElementId(elementId);
        edge.setSemanticElement(reference);

        (fullModel.getNotationModel()).getElements().add(edge);

    }

    private NotationElement findElement(BGEMFModelState model, String elementId) {
        return model.getNotationModel().getElements()
                .stream().filter(notationElement -> notationElement.getSemanticElement().getElementId().equals(elementId))
                .findFirst().get();
    }
}
