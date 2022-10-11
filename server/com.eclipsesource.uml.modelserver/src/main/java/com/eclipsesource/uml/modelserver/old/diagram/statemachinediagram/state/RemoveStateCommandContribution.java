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
package com.eclipsesource.uml.modelserver.old.diagram.statemachinediagram.state;

public class RemoveStateCommandContribution { /*-{

   public static final String TYPE = "removeState";

   public static CCompoundCommand create(final String parentSemanticUri, final String semanticUri) {
      CCompoundCommand removeStateCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
      removeStateCommand.setType(TYPE);
      removeStateCommand.getProperties().put(PARENT_SEMANTIC_URI_FRAGMENT, parentSemanticUri);
      removeStateCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUri);
      return removeStateCommand;
   }

   @Override
   protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
      throws DecodingException {

      String parentSemanticUri = command.getProperties().get(PARENT_SEMANTIC_URI_FRAGMENT);
      String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);

      return new RemoveStateCompoundCommand(domain, modelUri, parentSemanticUri, semanticUriFragment);
   }
   */
}
