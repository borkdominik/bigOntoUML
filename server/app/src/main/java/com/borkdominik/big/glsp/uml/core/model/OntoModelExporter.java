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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OntoModelExporter {

    public void exportModel(String filePath, BGEMFModelState model, EMFIdGenerator idGenerator) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        var contents = new ArrayList<JUmlElement>();

        Model umlModel = (Model) model.getSemanticModel();
        for (PackageableElement element : umlModel.getPackagedElements()) {
            if (element instanceof Class) {
                contents.add(exportClass((Class) element, idGenerator));
            } else if (element instanceof Association) {
                contents.add(exportAssociation((Association) element, objectMapper, idGenerator));
            }
        }

        var packageNode = objectMapper.createObjectNode();
        packageNode.putPOJO("contents", contents);
        packageNode.put("id", "HW27Sf6GAqAAbwBO_root");
        packageNode.put("type", "Package");
        packageNode.put("name", "ontology");

        root.set("model", packageNode);
        root.put("type", "Project");
        root.put("name", "ontology");
        root.put("id", "HW27Sf6GAqAAbwBO");


        root.set("diagrams", exportDiagrams(model, objectMapper, idGenerator));

        try (FileWriter writer = new FileWriter(filePath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, root);
        } catch (IOException e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }

    private JUmlElement exportClass(Class umlClass, EMFIdGenerator idGenerator) {
        return new JUmlElement(
                idGenerator.getOrCreateId(umlClass),
                umlClass.getName(),
                null,
                "Class",
                umlClass.getAppliedStereotypes().stream().map(NamedElement::getName).map(String::toLowerCase).findFirst().orElse(null),
                null
        );
    }

    private JUmlElement exportAssociation(Association association, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        var properties = new ArrayList<JProperty>();

        for (Property end : association.getMemberEnds()) {
            properties.add(new JProperty(
                    idGenerator.getOrCreateId(end.getType()),
                    "Property",
                    new JPropertyType(idGenerator.getOrCreateId(end.getType()),"Class")
            ));
        }

        return new JUmlElement(
                idGenerator.getOrCreateId(association),
                association.getName(),
                null,
                "Relation",
                association.getAppliedStereotypes().stream().map(NamedElement::getName).map(String::toLowerCase).findFirst().orElse(null),
                properties
        );
    }

    private ArrayNode exportDiagrams(BGEMFModelState model, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        ArrayNode diagramsArray = objectMapper.createArrayNode();
        ObjectNode diagramNode = objectMapper.createObjectNode();
        ArrayNode diagramContents = objectMapper.createArrayNode();

        diagramNode.put("id", "YQHz5G6GAqACBBeg");
        diagramNode.put("name", "Diagram");
        diagramNode.put("type", "Diagram");

        ObjectNode diagramOwner = objectMapper.createObjectNode();
        diagramOwner.put("id", "HW27Sf6GAqAAbwBO_root");
        diagramOwner.put("type", "Package");
        diagramNode.set("owner", diagramOwner);

        for (NotationElement notationElement : model.getNotationModel().getElements()) {
            if (notationElement instanceof Shape shape) {
                ObjectNode elementNode = objectMapper.createObjectNode();
                elementNode.put("type", "ClassView");
                elementNode.put("id", shape.getSemanticElement().getElementId() + "_diagram");

                ObjectNode modelElementNode = objectMapper.createObjectNode();
                modelElementNode.put("id", shape.getSemanticElement().getElementId());
                modelElementNode.put("type", "Class");
                elementNode.set("modelElement", modelElementNode);
                elementNode.putPOJO("shape", new JShape(
                        shape.getPosition().getX(),
                        shape.getPosition().getY(),
                        80,
                        40,
                        "Rectangle",
                        shape.getSemanticElement().getElementId() + "_shape"
                ));
                diagramContents.add(elementNode);
            }
        }
        diagramNode.set("contents", diagramContents);
        diagramsArray.add(diagramNode);

        return diagramsArray;
    }

    record JShape(double x, double y, int width, int height, String shape, String id) {
    }

    record JUmlElement(String id, String name, List<JUmlElement> content, String type, String stereotype,
                       List<JProperty> properties) {
    }

    record JProperty(String id, String type, JPropertyType propertyType) {
    }

    record JPropertyType(String id, String type) {
    }
}
