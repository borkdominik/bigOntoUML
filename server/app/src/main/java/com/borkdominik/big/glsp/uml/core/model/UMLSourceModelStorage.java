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
import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.glsp.server.emf.model.notation.NotationFactory;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import java.io.IOException;

public class UMLSourceModelStorage extends BGEMFSourceModelStorage {

    @Inject
    protected EMFIdGenerator idGenerator;

    public static EObject metaModel; //TODO: lol fix this


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
        super.loadSourceModel(action);

        var profile = loadOntoUmlProfile();

        profile.define();

        UMLSourceModelStorage.metaModel = profile;
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

        var profile = loadOntoUmlProfile();

        referenceMetaclass(profile, UMLPackage.Literals.PROPERTY.getName());

        profile.define();
        model.applyProfile(profile);
        try {
            unotationResource.save(null);
        } catch (IOException e) {
            throw new GLSPServerException("Failed to save file", e);
        }
    }

    private Profile loadOntoUmlProfile() {
        return (Profile) this.loadResource(
                        this.getOrCreateEditingDomain().getResourceSet(),
                        URI.createURI("/home/benjamin/projects/bigOntoUML/server/model/ontouml/OntoUML.uml"))
                .get();
    }

    org.eclipse.uml2.uml.Class referenceMetaclass(
            Profile profile, String name) {

        Model umlMetamodel = (Model) load(URI
                .createURI(UMLResource.UML_METAMODEL_URI));

        org.eclipse.uml2.uml.Class metaclass = (org.eclipse.uml2.uml.Class) umlMetamodel
                .getOwnedType(name);

        profile.createMetaclassReference(metaclass);

        return metaclass;
    }

    org.eclipse.uml2.uml.Package load(URI uri) {

            // Load the requested resource
            Resource resource = RESOURCE_SET.getResource(uri, true);

            // Get the first (should be only) package from it
            return (org.eclipse.uml2.uml.Package) EcoreUtil
                    .getObjectByType(resource.getContents(),
                            UMLPackage.Literals.PACKAGE);
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
}
