package com.borkdominik.big.glsp.uml.core.model;

import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.NotationElement;
import org.eclipse.glsp.server.emf.model.notation.Shape;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OntoModelExporter {

    public void exportModel(String filePath, BGEMFModelState model, EMFIdGenerator idGenerator) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        ObjectNode modelNode = objectMapper.createObjectNode();
        ArrayNode contents = objectMapper.createArrayNode();

        Model umlModel = (Model) model.getSemanticModel();
        for (PackageableElement element : umlModel.getPackagedElements()) {
            if (element instanceof Class) {
                contents.add(exportClass((Class) element, objectMapper, idGenerator));
            } else if (element instanceof Association) {
                contents.add(exportAssociation((Association) element, objectMapper, idGenerator));
            }
        }
        modelNode.set("contents", contents);
        root.set("model", modelNode);

        root.set("diagrams", exportDiagrams(model, objectMapper, idGenerator));

        try (FileWriter writer = new FileWriter(filePath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, root);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }

    private ObjectNode exportClass(Class umlClass, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        ObjectNode classNode = objectMapper.createObjectNode();
        classNode.put("id", idGenerator.getOrCreateId(umlClass));
        classNode.put("type", "Class");
        classNode.put("name", umlClass.getName());
        if (!umlClass.getAppliedStereotypes().isEmpty()) {
            classNode.put("stereotype", umlClass.getAppliedStereotypes().get(0).getName());
        }
        return classNode;
    }

    private ObjectNode exportAssociation(Association association, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        ObjectNode associationNode = objectMapper.createObjectNode();
        associationNode.put("id", idGenerator.getOrCreateId(association));
        associationNode.put("type", "Relation");
        ArrayNode properties = objectMapper.createArrayNode();

        for (Property end : association.getMemberEnds()) {
            ObjectNode propertyNode = objectMapper.createObjectNode();
            ObjectNode propertyType = objectMapper.createObjectNode();
            propertyType.put("id", idGenerator.getOrCreateId(end.getType()));
            propertyNode.set("propertyType", propertyType);
            properties.add(propertyNode);
        }

        associationNode.set("properties", properties);
        if (!association.getAppliedStereotypes().isEmpty()) {
            associationNode.put("stereotype", association.getAppliedStereotypes().get(0).getName().toLowerCase());
        }
        return associationNode;
    }

    private ArrayNode exportDiagrams(BGEMFModelState model, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        ArrayNode diagramsArray = objectMapper.createArrayNode();
        ObjectNode diagramNode = objectMapper.createObjectNode();
        ArrayNode diagramContents = objectMapper.createArrayNode();

        for (NotationElement notationElement : model.getNotationModel().getElements()) {
            if (notationElement instanceof Shape) {
                Shape shape = (Shape) notationElement;
                ObjectNode elementNode = objectMapper.createObjectNode();
                elementNode.put("type", "ClassView");
                ObjectNode modelElementNode = objectMapper.createObjectNode();
                modelElementNode.put("id", shape.getSemanticElement().getElementId());
                elementNode.set("modelElement", modelElementNode);
                ObjectNode shapeNode = objectMapper.createObjectNode();
                shapeNode.put("x", shape.getPosition().getX());
                shapeNode.put("y", shape.getPosition().getY());
                elementNode.set("shape", shapeNode);
                diagramContents.add(elementNode);
            }
        }
        diagramNode.set("contents", diagramContents);
        diagramsArray.add(diagramNode);

        return diagramsArray;
    }
}
