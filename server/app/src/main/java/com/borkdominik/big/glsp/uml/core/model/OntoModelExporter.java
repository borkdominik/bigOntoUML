package com.borkdominik.big.glsp.uml.core.model;

import com.borkdominik.big.glsp.server.core.model.BGEMFModelState;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.Edge;
import org.eclipse.glsp.server.emf.model.notation.NotationElement;
import org.eclipse.glsp.server.emf.model.notation.Shape;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OntoModelExporter {

    public void exportModel(String filePath, BGEMFModelState model, EMFIdGenerator idGenerator) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        var contents = new ArrayList<JUmlElementInterface>();

        Model umlModel = (Model) model.getSemanticModel();
        for (PackageableElement element : umlModel.getPackagedElements()) {
            if (element instanceof Class) {
                contents.add(exportClass((Class) element, idGenerator));
                ((Class) element).getGeneralizations().forEach(generalization -> {
                    contents.add(exportGeneralization(generalization, idGenerator));
                });
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
                    idGenerator.getOrCreateId(end.getType()) + "property",
                    "Property",
                    new JPropertyType(idGenerator.getOrCreateId(end.getType()), "Class")
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

    private JUmlElementInterface exportGeneralization(Generalization generalization, EMFIdGenerator idGenerator) {
        return new JUmlGeneralisation(
                idGenerator.getOrCreateId(generalization),
                "Generalization",
                new JUmlElement(idGenerator.getOrCreateId(generalization.getGeneral()), null, null, "Class", null, null),
                new JUmlElement(idGenerator.getOrCreateId(generalization.getSpecific()), null, null, "Class", null, null)
        );
    }

    private ArrayNode exportDiagrams(BGEMFModelState model, ObjectMapper objectMapper, EMFIdGenerator idGenerator) {
        ArrayNode diagramsArray = objectMapper.createArrayNode();
        ObjectNode diagramNode = objectMapper.createObjectNode();
        var diagramContents = new ArrayList<>();

        diagramNode.put("id", "YQHz5G6GAqACBBeg");
        diagramNode.put("name", "Diagram");
        diagramNode.put("type", "Diagram");

        ObjectNode diagramOwner = objectMapper.createObjectNode();
        diagramOwner.put("id", "HW27Sf6GAqAAbwBO_root");
        diagramOwner.put("type", "Package");
        diagramNode.set("owner", diagramOwner);

        for (NotationElement notationElement : model.getNotationModel().getElements()) {
            if (notationElement instanceof Shape shape) {
                diagramContents.add(getjClassView(shape));
            } else if (notationElement instanceof Edge edge) {
                var semanticElement = notationElement.getSemanticElement().getResolvedSemanticElement();
                if (semanticElement instanceof Generalization generalization) {
                    var generalId = idGenerator.getOrCreateId(generalization.getGeneral()) + "_diagram";
                    var specificId = idGenerator.getOrCreateId(generalization.getSpecific()) + "_diagram";

                    var relationNode = new JRelation(
                            edge.getSemanticElement().getElementId() + "_diagram",
                            "GeneralizationView",
                            new JProperty(notationElement.getSemanticElement().getElementId(), "Generalization", null),
                            new JProperty(specificId, "ClassView", null),
                            new JProperty(generalId, "ClassView", null)

                    );
                    diagramContents.add(relationNode);
                } else if (notationElement.getSemanticElement().getResolvedSemanticElement() instanceof Association association) {
                    var sourceId = idGenerator.getOrCreateId(association.getMemberEnds().get(1).getType()) + "_diagram";
                    var targetId = idGenerator.getOrCreateId(association.getMemberEnds().get(0).getType()) + "_diagram";

                    var relationNode = new JRelation(
                            edge.getSemanticElement().getElementId() + "_diagram",
                            "RelationView",
                            new JProperty(notationElement.getSemanticElement().getElementId(), "Relation", null),
                            new JProperty(sourceId, "ClassView", null),
                            new JProperty(targetId, "ClassView", null)

                    );
                    diagramContents.add(relationNode);
                }
            }
        }
        diagramNode.putPOJO("contents", diagramContents);
        diagramsArray.add(diagramNode);
        return diagramsArray;
    }

    private static JClassView getjClassView(Shape shape) {
        var jshape = new JShape(
                shape.getPosition().getX(),
                shape.getPosition().getY(),
                80,
                40,
                "Rectangle",
                shape.getSemanticElement().getElementId() + "_shape"
        );
        var modelElement = new JProperty(
                shape.getSemanticElement().getElementId(),
                "Class",
                null
        );
        return new JClassView(
                shape.getSemanticElement().getElementId() + "_diagram",
                "ClassView",
                jshape,
                modelElement
        );
    }

    record JShape(double x, double y, int width, int height, String shape, String id) {
    }

    record JRelation(String id, String type, JProperty modelElement, JProperty source,
                     JProperty target) implements JUmlElementInterface {
    }

    record JClassView(String id, String type, JShape shape,
                      JProperty modelElement) implements JUmlElementInterface {
    }

    interface JUmlElementInterface {
    }

    record JUmlElement(String id, String name, List<JUmlElement> content, String type, String stereotype,
                       List<JProperty> properties) implements JUmlElementInterface {
    }

    record JUmlGeneralisation(String id, String type, JUmlElement general,
                              JUmlElement specific) implements JUmlElementInterface {
    }

    record JProperty(String id, String type, JPropertyType propertyType) {
    }

    record JPropertyType(String id, String type) {
    }
}
