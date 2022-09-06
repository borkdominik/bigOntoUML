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
package com.eclipsesource.uml.glsp.uml.class_diagram.operations;

import java.util.List;

import org.eclipse.emfcloud.modelserver.glsp.operations.handlers.EMSBasicCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.Operation;
import org.eclipse.glsp.server.types.GLSPServerException;

import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.uml.class_diagram.ClassModelServerAccess;
import com.eclipsesource.uml.glsp.util.UmlConfig.Types;
import com.google.common.collect.Lists;

public class CreateClassDiagramNodeOperationHandler
   extends EMSBasicCreateOperationHandler<CreateNodeOperation, ClassModelServerAccess> {

   public CreateClassDiagramNodeOperationHandler() {
      super(handledElementTypeIds);
   }

   private static final List<String> handledElementTypeIds = Lists.newArrayList(
      Types.CLASS, Types.INTERFACE, Types.ENUMERATION, Types.ABSTRACT_CLASS);

   @Override
   public boolean handles(final Operation execAction) {
      if (execAction instanceof CreateNodeOperation) {
         CreateNodeOperation action = (CreateNodeOperation) execAction;
         return handledElementTypeIds.contains(action.getElementTypeId());
      }
      return false;
   }

   protected UmlModelState getUmlModelState() { return (UmlModelState) getEMSModelState(); }

   @Override
   public void executeOperation(final CreateNodeOperation operation, final ClassModelServerAccess modelAccess) {
      boolean isAbstract = false;
      if (Types.CLASS.equals(operation.getElementTypeId())
         || Types.ABSTRACT_CLASS.equals(operation.getElementTypeId())) {

         if (Types.ABSTRACT_CLASS.equals(operation.getElementTypeId())) {
            isAbstract = true;
         }
         modelAccess.addClass(getUmlModelState(), operation.getLocation(), isAbstract)
            .thenAccept(response -> {
               if (!response.body()) {
                  throw new GLSPServerException("Could not execute create operation on new Class node");
               }
            });
      } else if (Types.INTERFACE.equals(operation.getElementTypeId())) {
         modelAccess.addInterface(getUmlModelState(), operation.getLocation())
            .thenAccept(response -> {
               if (!response.body()) {
                  throw new GLSPServerException("Could not execute create operation on new Interface node");
               }
            });
      } else if (Types.ENUMERATION.equals(operation.getElementTypeId())) {
         modelAccess.addEnumeration(getUmlModelState(), operation.getLocation())
            .thenAccept(response -> {
               if (!response.body()) {
                  throw new GLSPServerException("Could not execute create operation on new Enumeration node");
               }
            });
      }
   }

   @Override
   public String getLabel() { return "Create uml classifier"; }

}
