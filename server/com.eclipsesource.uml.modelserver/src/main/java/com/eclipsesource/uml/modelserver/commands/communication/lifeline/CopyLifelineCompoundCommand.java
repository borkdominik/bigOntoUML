/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.commands.communication.lifeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;

import com.eclipsesource.uml.modelserver.commands.util.UmlNotationCommandUtil;

public class CopyLifelineCompoundCommand extends CompoundCommand {
   private final EditingDomain domain;
   private final URI modelUri;
   private final Map<String, Lifeline> mappings = new HashMap<>();

   public CopyLifelineCompoundCommand(final EditingDomain domain, final URI modelUri,
      final List<LifelineCopyableProperties> propertiesList, final Interaction parentInteraction) {
      this.domain = domain;
      this.modelUri = modelUri;

      propertiesList.forEach(properties -> {
         var newLifeline = appendLifeline(properties, parentInteraction);
         mappings.put(properties.getSemantic().id, newLifeline);
      });
   }

   public Map<String, Lifeline> getMappings() { return mappings; }

   private Lifeline appendLifeline(final LifelineCopyableProperties properties, final Interaction parentInteraction) {
      var command = new CopyLifelineCommand(domain, modelUri, properties, parentInteraction);
      var position = UmlNotationCommandUtil.getGPoint(properties.getNotation().position);

      this.append(command);
      this.append(new AddLifelineShapeCommand(domain, modelUri, position, () -> command.getNewLifeline()));

      return command.getNewLifeline();
   }
}
