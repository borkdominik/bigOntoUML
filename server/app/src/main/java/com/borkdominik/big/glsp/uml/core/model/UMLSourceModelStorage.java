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
package com.borkdominik.big.glsp.uml.core.model;

import com.borkdominik.big.glsp.server.core.handler.action.new_file.BGRequestNewFileAction;
import com.borkdominik.big.glsp.server.core.model.integrations.BGEMFSourceModelStorage;
import com.borkdominik.big.glsp.uml.unotation.UMLDiagram;
import com.borkdominik.big.glsp.uml.unotation.UnotationFactory;
import com.borkdominik.big.glsp.uml.unotation.UnotationPackage;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.NotationFactory;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import java.io.IOException;
import java.util.Collections;

public class UMLSourceModelStorage extends BGEMFSourceModelStorage {

    @Inject
    protected EMFIdGenerator idGenerator;

    private static final ResourceSet RESOURCE_SET;

    static {
        // Create a resource-set to contain the resource(s) that we load and
        // save
        RESOURCE_SET = new ResourceSetImpl();

        // Initialize registrations of resource factories, library models,
        // profiles, Ecore metadata, and other dependencies required for
        // serializing and working with UML resources. This is only necessary in
        // applications that are not hosted in the Eclipse platform run-time, in
        // which case these registrations are discovered automatically from
        // Eclipse extension points.
        UMLResourcesUtil.init(RESOURCE_SET);

    }

    @Override
    protected ResourceSet setupResourceSet(final ResourceSet resourceSet) {
        super.setupResourceSet(resourceSet);
        resourceSet.getPackageRegistry().put(UMLPackage.eINSTANCE.getNsURI(), UMLPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(UnotationPackage.eINSTANCE.getNsURI(), UnotationPackage.eINSTANCE);
        return resourceSet;
    }

    @Override
    public void loadSourceModel(final RequestModelAction action) {

        var profile = loadOntoUmlProfile();
        this.getOrCreateEditingDomain().getResourceSet()
                .getPackageRegistry().put("https://www.vorstieg.eu/ontouml.uml", profile.getEAnnotations().get(0).getContents().get(0));

        var sourceURI = ClientOptionsUtil.getSourceUri(action.getOptions())
                .orElseThrow(() -> new GLSPServerException("No source URI given to load model!"));
        // FIX: Handle the source URI correctly
        var resourceURI = URI.createURI(sourceURI);

        var editingDomain = getOrCreateEditingDomain();

        Resource resource = editingDomain.getResourceSet().getResource(resourceURI, true);
        if (resource == null) {
            throw new GLSPServerException("Failed to load resource: " + resourceURI);
        }

        var model = resource.getContents().stream().filter(m -> m instanceof Model).map(m -> (Model) m).findFirst().get();

        this.modelState.setSemanticModel(model);
        loadNotationModel(editingDomain.getResourceSet(), resourceURI, action);

    }

    @Override
    protected URI deriveNotationModelURI(final URI sourceURI) {
        return sourceURI.trimFileExtension().appendFileExtension("unotation");
    }

    @Override
    protected void doCreateSourceModel(final ResourceSet resourceSet, final URI resourceURI,
                                       final BGRequestNewFileAction action) {
        var packageRegistry = resourceSet.getPackageRegistry();

        packageRegistry.entrySet().stream()
                .filter(entry -> {
                    var value = entry.getValue();
                    return value instanceof UMLPackage || value instanceof UnotationPackage;
                })
                .forEach((entry) -> {
                    var ePackage = (EPackage) entry.getValue();
                    doCreateResource(resourceSet, ePackage, resourceURI);
                });

        var umlFile = resourceURI.appendFileExtension(UMLPackage.eINSTANCE.getNsPrefix());
        var umlResource = resourceSet.getResource(umlFile, false);
        var unotationFile = resourceURI.appendFileExtension(UnotationPackage.eINSTANCE.getNsPrefix());
        var unotationResource = resourceSet.getResource(unotationFile, false);

        var model = (Model) umlResource.getAllContents().next();
        var diagram = (UMLDiagram) unotationResource.getAllContents().next();
        var semanticProxy = NotationFactory.eINSTANCE
                .createSemanticElementReference();
        semanticProxy.setElementId(idGenerator.getOrCreateId(model));
        diagram.setSemanticElement(semanticProxy);
        diagram.setDiagramType(action.getDiagramType());

        model.setName("BigOntoUml");

        var profile = loadOntoUmlProfile();
        model.getPackagedElements().add(profile);

        model.applyProfile(profile);

        try {
            unotationResource.save(null);
            umlResource.save(null);
        } catch (IOException e) {
            throw new GLSPServerException("Failed to save file", e);
        }
    }

    private Profile loadOntoUmlProfile() {

        return (Profile)this.loadResource(
                        this.getOrCreateEditingDomain().getResourceSet(),
                        URI.createURI("/home/benjamin/projects/bigOntoUML/server/model/ontouml/OntoUML.uml")).get();
    }

    @Override
    protected EObject doCreateResourceContent(final EPackage ePackage) {
        if (ePackage.equals(UMLPackage.eINSTANCE)) {
            return UMLFactory.eINSTANCE.createModel();
        } else if (ePackage.equals(UnotationPackage.eINSTANCE)) {
            return UnotationFactory.eINSTANCE.createUMLDiagram();
        }

        return super.doCreateResourceContent(ePackage);
    }

    @Override
    public void saveSourceModel(final SaveModelAction action) {
        for (Resource resource : modelState.getResourceSet().getResources()) {
            if (resource.getURI() != null && resource.getURI().isFile()) {
                try {
                    resource.save(Collections.EMPTY_MAP);
                } catch (IOException e) {
                    throw new GLSPServerException("Could not save model to file: " + resource.getURI(), e);
                }
            }
        }
    }
}
