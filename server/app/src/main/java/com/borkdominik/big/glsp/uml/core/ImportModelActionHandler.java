/********************************************************************************
 * Copyright (c) 2024 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.uml.core;

import com.borkdominik.big.glsp.server.core.handler.action.integrations.BGEMFActionHandler;
import com.borkdominik.big.glsp.server.core.handler.action.new_file.BGNewFileResponseAction;
import com.borkdominik.big.glsp.server.core.handler.action.new_file.BGRequestNewFileAction;
import com.borkdominik.big.glsp.server.core.model.BGModelState;
import com.borkdominik.big.glsp.server.core.model.integrations.BGEMFModelStateImpl;
import com.borkdominik.big.glsp.server.core.model.integrations.BGEMFSourceModelStorage;
import com.borkdominik.big.glsp.uml.core.model.OntoModelImporter;
import com.borkdominik.big.glsp.uml.core.model.UMLSourceModelStorage;
import com.google.inject.Inject;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.notation.EMFNotationModelState;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.uml2.uml.Model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ImportModelActionHandler extends BGEMFActionHandler<ImportModelAction> {

    @Inject
    protected BGModelState modelState;
    @Inject
    protected EMFIdGenerator idGenerator;
    @Inject
    protected UMLSourceModelStorage umlSourceModelStorage;


    @Override
    protected List<Action> executeAction(final ImportModelAction actualAction) {
        var umlFilePath = actualAction.getOptions().get("modelUri") + "/" + actualAction.getOptions().get("filename");
        var hackOptions = Map.of("sourceUri", umlFilePath);
        var hack = new BGRequestNewFileAction(hackOptions);
        hack.setDiagramType("ONTO_CLASS");
        var uri = umlSourceModelStorage.createSourceModel(hack);

        var hack2 = new RequestModelAction(Map.of("sourceUri", uri.path() + ".uml"));
        umlSourceModelStorage.loadSourceModel(hack2);

        var importModelUri = actualAction.getOptions().get("jsonModelUri");
        new OntoModelImporter().importModel(importModelUri, ((BGEMFModelStateImpl) modelState), idGenerator);

        var hack3 = new SaveModelAction(uri.path() + ".uml");
        umlSourceModelStorage.saveSourceModel(hack3);
        return Collections.emptyList();
    }
}
