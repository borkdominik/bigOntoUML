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
package com.eclipsesource.uml.glsp.uml.elements.realization.gmodel;

import java.util.List;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.uml2.uml.Realization;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.sdk.cdk.GModelContext;
import com.eclipsesource.uml.glsp.sdk.cdk.base.GCProvider;
import com.eclipsesource.uml.glsp.sdk.cdk.gmodel.GCModelList;
import com.eclipsesource.uml.glsp.sdk.utils.StreamUtils;
import com.eclipsesource.uml.glsp.uml.elements.abstraction.gmodel.GAbstractionBuilder;

public class GRealizationBuilder<TOrigin extends Realization> extends GAbstractionBuilder<TOrigin> {

   public GRealizationBuilder(final GModelContext context, final TOrigin origin, final String type) {
      super(context, origin, type);
   }

   @Override
   protected List<GCProvider> createComponentChildren(final GEdge gmodelRoot, final GCModelList<?, ?> componentRoot) {
      return List.of();
   }

   @Override
   protected List<String> getRootGModelCss() {
      return StreamUtils.concat(super.getDefaultCss(), List.of(CoreCSS.EDGE_DASHED, CoreCSS.Marker.TRIANGLE_EMPTY.end()));
   }
}