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
package com.eclipsesource.uml.modelserver.uml.diagram.sequence_diagram.commands.executionOccurrence;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emfcloud.modelserver.command.CCommand;
import org.eclipse.emfcloud.modelserver.common.codecs.DecodingException;
import org.eclipse.emfcloud.modelserver.edit.command.BasicCommandContribution;
import org.eclipse.uml2.uml.OccurrenceSpecification;

import com.eclipsesource.uml.modelserver.core.commands.noop.NoopCommand;
import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.codec.ContributionEncoder;

public final class UpdateExecutionOccurrenceContribution extends BasicCommandContribution<Command> {

   public static final String TYPE = "sequence:update_executionOccurrence";

   public static CCommand create(final OccurrenceSpecification semanticElement,
      final UpdateExecutionOccurrenceArgument argument) {
      return new ContributionEncoder()
         .type(TYPE)
         .element(semanticElement)
         .embedJson(argument)
         .ccommand();
   }

   @Override
   protected Command toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
      throws DecodingException {
      var decoder = new ContributionDecoder(modelUri, domain, command);

      var context = decoder.context();
      var element = decoder.element(OccurrenceSpecification.class);
      var updateArgument = decoder.embedJson(UpdateExecutionOccurrenceArgument.class);

      return element
         .<Command> map(e -> new UpdateExecutionOccurrenceSemanticCommand(context, e, updateArgument))
         .orElse(new NoopCommand());
   }

}