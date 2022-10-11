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
package com.eclipsesource.uml.modelserver.diagram.communication.lifeline;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.glsp.notation.commands.RemoveNotationElementCommand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

import com.eclipsesource.uml.modelserver.diagram.communication.message.RemoveMessageCompoundCommand;
import com.eclipsesource.uml.modelserver.diagram.util.UmlNotationCommandUtil;
import com.eclipsesource.uml.modelserver.diagram.util.UmlSemanticCommandUtil;

public class RemoveLifelineCompoundCommand extends CompoundCommand {

   public RemoveLifelineCompoundCommand(final EditingDomain domain, final URI modelUri,
      final String semanticUriFragment) {

      this.append(new RemoveLifelineCommand(domain, modelUri, semanticUriFragment));
      this.append(new RemoveNotationElementCommand(domain, modelUri, semanticUriFragment));

      var umlModel = UmlSemanticCommandUtil.getModel(modelUri, domain);
      var lifelineToRemove = UmlSemanticCommandUtil.getElement(umlModel, semanticUriFragment, Lifeline.class);

      lifelineToRemove.getCoveredBys().forEach(fragment -> {
         var specification = (MessageOccurrenceSpecification) fragment;
         var message = specification.getMessage();

         String messageUriFragment = UmlNotationCommandUtil
            .getSemanticProxyUriElement(message);

         this.append(new RemoveMessageCompoundCommand(domain, modelUri, messageUriFragment));
      });

   }

}
