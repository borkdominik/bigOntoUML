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
package com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.commands.upackage;

import java.util.Optional;

import org.eclipse.uml2.uml.VisibilityKind;

import com.eclipsesource.uml.modelserver.uml.diagram.package_diagram.util.PackageUtils;

public final class UpdatePackageArgument {
   private String name;
   private String label;
   private String uri;
   private VisibilityKind visibilityKind;

   public Optional<String> name() {
      return Optional.ofNullable(name);
   }

   public Optional<String> label() {
      return Optional.ofNullable(label);
   }

   public Optional<String> uri() {
      return Optional.ofNullable(uri);
   }

   public Optional<VisibilityKind> visibilityKind() {
      return Optional.ofNullable(visibilityKind);
   }

   public static final class Builder {
      private final UpdatePackageArgument argument = new UpdatePackageArgument();

      public Builder name(final String value) {
         argument.name = value;
         return this;
      }

      public Builder label(final String value) {
         argument.label = value;
         return this;
      }

      public Builder uri(final String value) {
         argument.uri = value;
         return this;
      }

      public Builder visibilityKind(final VisibilityKind value) {
         argument.visibilityKind = value;
         return this;
      }

      public UpdatePackageArgument get() {
         PackageUtils.validateVisiblity(argument.visibilityKind);
         return argument;
      }
   }
}
