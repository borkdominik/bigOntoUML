/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.manifest;

import org.eclipse.emfcloud.modelserver.edit.CommandContribution;

import com.eclipsesource.uml.modelserver.core.manifest.DiagramManifest;
import com.eclipsesource.uml.modelserver.core.manifest.contributions.CommandCodecContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.association.AddAssociationContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.association.RemoveAssociationContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.association.SetAssociationEndMultiplicityContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.association.SetAssociationEndNameContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.enumeration.AddEnumerationContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.enumeration.RemoveEnumerationContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.enumeration.SetEnumerationNameContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.AddPropertyContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.RemovePropertyContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.SetPropertyBoundsContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.SetPropertyNameContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.property.SetPropertyTypeContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uclass.AddClassContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uclass.RemoveClassContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uclass.SetClassNameContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uinterface.AddInterfaceContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uinterface.RemoveInterfaceContribution;
import com.eclipsesource.uml.modelserver.uml.diagram.class_diagram.commands.uinterface.SetInterfaceNameContribution;
import com.google.inject.multibindings.MapBinder;

public class ClassManifest extends DiagramManifest implements CommandCodecContribution {
   @Override
   protected void configure() {
      super.configure();
      contributeCommandCodec(binder());
   }

   @Override
   public void contributeCommandCodec(final MapBinder<String, CommandContribution> multibinder) {
      // CLASS DIAGRAM
      // Class
      multibinder.addBinding(AddClassContribution.TYPE).to(AddClassContribution.class);
      multibinder.addBinding(RemoveClassContribution.TYPE).to(RemoveClassContribution.class);
      multibinder.addBinding(SetClassNameContribution.TYPE).to(SetClassNameContribution.class);
      // Property
      multibinder.addBinding(AddPropertyContribution.TYPE).to(AddPropertyContribution.class);
      multibinder.addBinding(RemovePropertyContribution.TYPE).to(RemovePropertyContribution.class);
      // multibinder.addBinding(SetPropertyContribution.TYPE,
      // SetPropertyContribution.class);
      multibinder.addBinding(SetPropertyNameContribution.TYPE).to(SetPropertyNameContribution.class);
      multibinder.addBinding(SetPropertyTypeContribution.TYPE).to(SetPropertyTypeContribution.class);
      multibinder.addBinding(SetPropertyBoundsContribution.TYPE).to(SetPropertyBoundsContribution.class);
      // Association
      multibinder.addBinding(AddAssociationContribution.TYPE).to(AddAssociationContribution.class);
      multibinder.addBinding(RemoveAssociationContribution.TYPE).to(RemoveAssociationContribution.class);
      multibinder.addBinding(SetAssociationEndNameContribution.TYPE).to(SetAssociationEndNameContribution.class);
      multibinder.addBinding(SetAssociationEndMultiplicityContribution.TYPE)
         .to(SetAssociationEndMultiplicityContribution.class);
      // Generalisation
      /*
       * multibinder.addBinding(AddClassGeneralizationContribution.TYPE,
       * AddClassGeneralizationContribution.class);
       * multibinder.addBinding(RemoveClassGeneralizationContribution.TYPE,
       * RemoveClassGeneralizationContribution.class);
       */
      // Interface
      multibinder.addBinding(AddInterfaceContribution.TYPE).to(AddInterfaceContribution.class);
      multibinder.addBinding(RemoveInterfaceContribution.TYPE).to(RemoveInterfaceContribution.class);
      multibinder.addBinding(SetInterfaceNameContribution.TYPE).to(SetInterfaceNameContribution.class);
      // Enumeration
      multibinder.addBinding(AddEnumerationContribution.TYPE).to(AddEnumerationContribution.class);
      multibinder.addBinding(RemoveEnumerationContribution.TYPE).to(RemoveEnumerationContribution.class);
      multibinder.addBinding(SetEnumerationNameContribution.TYPE).to(SetEnumerationNameContribution.class);

   }
}
