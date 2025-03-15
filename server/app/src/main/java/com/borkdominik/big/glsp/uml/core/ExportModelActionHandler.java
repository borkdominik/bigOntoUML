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
import com.borkdominik.big.glsp.server.core.model.BGModelState;
import com.borkdominik.big.glsp.server.core.model.integrations.BGEMFModelStateImpl;
import com.borkdominik.big.glsp.uml.core.model.OntoModelExporter;
import com.borkdominik.big.glsp.uml.core.model.OntoModelImporter;
import com.google.inject.Inject;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.emf.EMFIdGenerator;

import java.util.Collections;
import java.util.List;

public class ExportModelActionHandler extends BGEMFActionHandler<ExportModelAction> {

    @Inject
    protected BGModelState modelState;
    @Inject
    protected EMFIdGenerator idGenerator;


    @Override
    protected List<Action> executeAction(final ExportModelAction actualAction) {
        var uri = actualAction.getOptions().get("modelUri");
        new OntoModelExporter().exportModel(uri, ((BGEMFModelStateImpl) modelState), idGenerator);
        return Collections.emptyList();
    }
}
