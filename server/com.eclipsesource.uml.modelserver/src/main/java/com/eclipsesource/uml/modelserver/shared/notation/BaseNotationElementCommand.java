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
package com.eclipsesource.uml.modelserver.shared.notation;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.eclipsesource.uml.modelserver.shared.extension.NotationElementAccessor;
import com.eclipsesource.uml.modelserver.shared.extension.SemanticElementAccessor;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.unotation.UmlDiagram;

public abstract class BaseNotationElementCommand extends RecordingCommand {

   protected final UmlDiagram diagram;
   protected final ModelContext context;

   protected final SemanticElementAccessor semanticElementAccessor;
   protected final NotationElementAccessor notationElementAccessor;

   public BaseNotationElementCommand(final ModelContext context) {
      super((TransactionalEditingDomain) context.domain);

      this.context = context;

      this.notationElementAccessor = new NotationElementAccessor(context);
      this.semanticElementAccessor = new SemanticElementAccessor(context);

      this.diagram = notationElementAccessor.getDiagram();
   }

}
