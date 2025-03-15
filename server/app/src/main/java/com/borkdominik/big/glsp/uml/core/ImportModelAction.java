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

import com.borkdominik.big.glsp.server.core.handler.action.new_file.BGNewFileResponseAction;
import org.eclipse.glsp.server.actions.RequestAction;

import java.util.HashMap;
import java.util.Map;

public class ImportModelAction extends RequestAction<BGNewFileResponseAction> {
   public static final String KIND = "importModel";

   private final Map<String, String> options;

   public ImportModelAction() {
      super(KIND);
      options = new HashMap<>();
   }

   public ImportModelAction(final Map<String, String> options) {
      super(KIND);
      this.options = options;
   }

   public Map<String, String> getOptions() { return options; }
}
