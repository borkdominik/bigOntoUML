/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.uml.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.glsp.graph.GPoint;

import com.eclipsesource.uml.modelserver.core.commands.noop.NoopCommand;
import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.DeleteNotationElementCommand;
import com.eclipsesource.uml.modelserver.shared.semantic.CallbackSemanticCommand;
import com.eclipsesource.uml.modelserver.shared.utils.reflection.GenericsUtil;
import com.eclipsesource.uml.modelserver.uml.reference.CrossReferenceRemover;

public abstract class NodeCommandProvider<TElement extends EObject, TParent>
   extends ElementCommandProvider<TElement> {

   protected final Class<TParent> parentType;

   public NodeCommandProvider() {
      parentType = GenericsUtil.getClassParameter(getClass(), NodeCommandProvider.class, 1);
   }

   @Override
   public Command provideCreateCommand(final ModelContext context) {
      var decoder = new ContributionDecoder(context);

      var parent = decoder.parent(parentType).orElseThrow();
      var position = decoder.position().orElseThrow();

      var command = new CompoundCommand();
      createModifications(context, parent, position).forEach(command::append);
      return command;
   }

   protected abstract Collection<Command> createModifications(ModelContext context, TParent parent, GPoint position);

   @Override
   public Command provideDeleteCommand(final ModelContext context, final TElement element) {
      var command = new CompoundCommand();
      deleteModifications(context, element).forEach(command::append);
      return command;
   }

   protected Collection<Command> deleteModifications(final ModelContext context, final TElement element) {
      var commands = new ArrayList<Command>(List.of(
         new CallbackSemanticCommand(context, c -> {
            EcoreUtil.delete(element);
         }),
         new DeleteNotationElementCommand(context, element)));
      new CrossReferenceRemover(context).deleteCommandsFor(element).forEach(commands::add);
      return commands;
   }

   @Override
   public Command provideUpdateCommand(final ModelContext context, final TElement element) {
      var command = new CompoundCommand();
      updateModifications(context, element).forEach(command::append);
      return command;
   }

   protected Collection<Command> updateModifications(final ModelContext context, final TElement element) {
      return List.of(new NoopCommand());
   }
}