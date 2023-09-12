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
package com.eclipsesource.uml.glsp.uml.elements.literal_specification;

import com.eclipsesource.uml.glsp.uml.configuration.di.NodeConfigurationFactory;
import com.eclipsesource.uml.glsp.uml.elements.literal_specification.features.LiteralSpecificationPropertyMapper;
import com.eclipsesource.uml.glsp.uml.features.property_palette.di.PropertyMapperFactory;
import com.eclipsesource.uml.glsp.uml.handler.di.NodeOperationHandlerFactory;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public interface LiteralSpecificationFactory
   extends NodeConfigurationFactory, NodeOperationHandlerFactory, PropertyMapperFactory {
   @Override
   LiteralSpecificationConfiguration nodeConfiguration(Representation representation);

   @Override
   LiteralSpecificationOperationHandler nodeOperationHandler(Representation representation);

   @Override
   LiteralSpecificationPropertyMapper elementPropertyMapper(Representation representation);

}
