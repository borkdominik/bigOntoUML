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
package com.eclipsesource.uml.modelserver.old.diagram.classdiagram.association;

public class RemoveAssociationCommandContribution { /*-{

   public static final String TYPE = "removeAssociation";

   public static CCompoundCommand create(final String semanticUriFragment) {
      CCompoundCommand removeAssociationCommand = CCommandFactory.eINSTANCE.createCompoundCommand();
      removeAssociationCommand.setType(TYPE);
      removeAssociationCommand.getProperties().put(SEMANTIC_URI_FRAGMENT, semanticUriFragment);
      return removeAssociationCommand;
   }

   @Override
   protected CompoundCommand toServer(final URI modelUri, final EditingDomain domain, final CCommand command)
      throws DecodingException {

      String semanticUriFragment = command.getProperties().get(SEMANTIC_URI_FRAGMENT);
      return new RemoveAssociationCompoundCommand(domain, modelUri, semanticUriFragment);
   }
   */
}
