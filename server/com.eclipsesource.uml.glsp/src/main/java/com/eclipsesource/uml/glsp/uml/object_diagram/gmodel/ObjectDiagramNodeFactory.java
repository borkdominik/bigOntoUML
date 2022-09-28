package com.eclipsesource.uml.glsp.uml.object_diagram.gmodel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.uml.object_diagram.constants.ObjectCSS;
import com.eclipsesource.uml.glsp.uml.object_diagram.constants.ObjectTypes;
import com.eclipsesource.uml.glsp.utils.UmlConfig;
import com.eclipsesource.uml.glsp.utils.UmlConfig.CSS;
import com.eclipsesource.uml.glsp.utils.UmlIDUtil;
import com.eclipsesource.uml.modelserver.unotation.Shape;

public class ObjectDiagramNodeFactory extends ObjectAbstractGModelFactory<Classifier, GNode> {

   private final ObjectDiagramLabelFactory labelFactory;

   public ObjectDiagramNodeFactory(final UmlModelState modelState, final ObjectDiagramLabelFactory labelFactory) {
      super(modelState);
      this.labelFactory = labelFactory;
   }

   @Override
   public GNode create(final Classifier classifier) {
      System.out.println("in node factory");
      if (classifier instanceof Class) {
         System.out.println("in node factory instance spec");
         return createObjectNode((Class) classifier);
      }
      return null;
   }

   protected void applyShapeData(final Classifier classifier, final GNodeBuilder b) {
      modelState.getIndex().getNotation(classifier, Shape.class).ifPresent(shape -> {
         if (shape.getPosition() != null) {
            b.position(GraphUtil.copy(shape.getPosition()));
         }
         if (shape.getSize() != null) {
            b.size(GraphUtil.copy(shape.getSize()));
         }
      });
   }

   protected void applyShapeData(final Element element, final GNodeBuilder b) {
      modelState.getIndex().getNotation(element, Shape.class).ifPresent(shape -> {
         if (shape.getPosition() != null) {
            b.position(GraphUtil.copy(shape.getPosition()));
         } else if (shape.getSize() != null) {
            b.size(GraphUtil.copy(shape.getSize()));
         }
      });
   }

   // protected GNode
   protected GNode createObjectNode(final Class umlObject) {
      GNodeBuilder b = new GNodeBuilder(ObjectTypes.OBJECT)
         .id(toId(umlObject))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CSS.NODE)
         .add(buildObjectHeader(umlObject))
         .add(buildObjectAttributeCompartment(umlObject.getAttributes(), umlObject));
      applyShapeData(umlObject, b);
      return b.build();
   }

   // protected GCompartment buildObjectHeader(final InstanceSpecification umlObject) {
   protected GCompartment buildObjectHeader(final Class umlObject) {
      GCompartmentBuilder objectHeaderBuilder = new GCompartmentBuilder(UmlConfig.Types.COMPARTMENT_HEADER)
         .layout(GConstants.Layout.HBOX)
         .id(UmlIDUtil.createHeaderId(toId(umlObject)));

      // TODO: add icon later again
      /*
       * GCompartment objectHeaderIcon = new GCompartmentBuilder(getType(umlObject))
       * .id(UmlIDUtil.createHeaderIconId(toId(umlObject))).build();
       * objectHeaderBuilder.add(objectHeaderIcon);
       */

      GLabel objectHeaderLabel = new GLabelBuilder(UmlConfig.Types.LABEL_NAME)
         .id(UmlIDUtil.createHeaderLabelId(toId(umlObject)))
         .addCssClass(ObjectCSS.UNDERLINE)
         .text(umlObject.getName()).build();

      objectHeaderBuilder.add(objectHeaderLabel);

      return objectHeaderBuilder.build();
   }

   protected static String getType(final Classifier classifier) {
      // if (classifier instanceof InstanceSpecification) {
      if (classifier instanceof Class) {
         return ObjectTypes.ICON_OBJECT;
      }
      return "Classifier not found";
   }

   protected GCompartment buildObjectAttributeCompartment(final Collection<? extends Property> attributes,
      final Classifier parent) {
      GCompartmentBuilder objectAttributeBuilder = new GCompartmentBuilder(UmlConfig.Types.COMP)
         .id(UmlIDUtil.createChildCompartmentId(toId(parent))).layout(GConstants.Layout.VBOX);

      GLayoutOptions layoutOptions = new GLayoutOptions()
         .hAlign(GConstants.HAlign.LEFT)
         .resizeContainer(true);
      objectAttributeBuilder.layoutOptions(layoutOptions);

      List<GModelElement> attributeLabels = attributes.stream()
         .map(labelFactory::createPropertyLabel)
         .collect(Collectors.toList());
      objectAttributeBuilder.addAll(attributeLabels);

      return objectAttributeBuilder.build();
   }
}
