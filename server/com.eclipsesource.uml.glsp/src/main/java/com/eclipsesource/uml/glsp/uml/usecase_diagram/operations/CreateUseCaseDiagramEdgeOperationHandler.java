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
package com.eclipsesource.uml.glsp.uml.usecase_diagram.operations;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emfcloud.modelserver.glsp.operations.handlers.EMSBasicCreateOperationHandler;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.Operation;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.glsp.model.UmlModelIndex;
import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.uml.usecase_diagram.UseCaseModelServerAccess;
import com.eclipsesource.uml.glsp.uml.usecase_diagram.constants.UseCaseTypes;
import com.google.common.collect.Lists;

public class CreateUseCaseDiagramEdgeOperationHandler
   extends EMSBasicCreateOperationHandler<CreateEdgeOperation, UseCaseModelServerAccess> {

   public CreateUseCaseDiagramEdgeOperationHandler() {
      super(handledElementTypeIds);
   }

   /**
    * The Types specified in this list will be processed by this file.
    * If a type is not mentioned here, the request for that type will not be redirected here.
    */
   private static List<String> handledElementTypeIds = Lists.newArrayList(UseCaseTypes.USECASE_ASSOCIATION,
      UseCaseTypes.EXTEND,
      UseCaseTypes.INCLUDE, UseCaseTypes.GENERALIZATION);

   @Override
   public boolean handles(final Operation execAction) {
      if (execAction instanceof CreateEdgeOperation) {
         CreateEdgeOperation action = (CreateEdgeOperation) execAction;
         return handledElementTypeIds.contains(action.getElementTypeId());
      }
      return false;
   }

   protected UmlModelState getUmlModelState() { return (UmlModelState) getEMSModelState(); }

   @Override
   public void executeOperation(final CreateEdgeOperation operation, final UseCaseModelServerAccess modelAccess) {

      UmlModelState modelState = getUmlModelState();
      String elementTypeId = operation.getElementTypeId();

      UmlModelIndex modelIndex = modelState.getIndex();
      String target = operation.getTargetElementId();
      /*
       * if (target.endsWith("_anchor")) {
       * String pattern = "(.*)_anchor";
       * // Create a Pattern object
       * Pattern r = Pattern.compile(pattern);
       * // Now create matcher object.
       * Matcher m = r.matcher(target);
       * if (m.find()) {
       * target = m.group(1);
       * }
       * }
       */

      EObject sourceClassifier = getOrThrow(modelIndex.getSemantic(operation.getSourceElementId()),
         "No semantic Element found for source element with id " + operation.getSourceElementId());
      EObject targetClassifier = getOrThrow(modelIndex.getSemantic(target),
         "No semantic Element found for target element with id " + operation.getTargetElementId());

      switch (elementTypeId) {
         case UseCaseTypes.EXTEND:
            if (targetClassifier instanceof ExtensionPoint) {
               modelAccess.addExtend(modelState, (UseCase) sourceClassifier, (ExtensionPoint) targetClassifier)
                  .thenAccept(response -> {
                     if (!response.body()) {
                        throw new GLSPServerException("Could not execute create operation on new UCD Extend edge");
                     }
                  });
            } else {
               modelAccess.addExtend(modelState, (UseCase) sourceClassifier, (UseCase) targetClassifier)
                  .thenAccept(response -> {
                     if (!response.body()) {
                        throw new GLSPServerException("Could not execute create operation on new UCD Extend edge");
                     }
                  });
            }
            break;
         case UseCaseTypes.INCLUDE:
            modelAccess.addInclude(modelState, (UseCase) sourceClassifier, (UseCase) targetClassifier)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException("Could not execute create operation on new UCD Include edge");
                  }
               });
            break;
         case UseCaseTypes.GENERALIZATION:
            modelAccess.addGeneralization(modelState, (Classifier) targetClassifier, (Classifier) sourceClassifier)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException("Could not execute create operation on new UCD Generalization edge");
                  }
               });
            break;
         case UseCaseTypes.USECASE_ASSOCIATION:
            modelAccess.addUseCaseAssociation(modelState, (Classifier) targetClassifier, (Classifier) sourceClassifier)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException("Could not execute create operation on new Association edge");
                  }
               });
            break;
      }
   }

   @Override
   public String getLabel() { return "Create uml edge"; }

}
