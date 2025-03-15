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
package com.borkdominik.big.glsp.uml.core.pattern;

import com.borkdominik.big.glsp.server.core.handler.action.new_file.BGNewFileResponseAction;
import org.eclipse.glsp.server.actions.RequestAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhasePatternAction extends RequestAction<BGNewFileResponseAction> {
   public static final String KIND = "phasePattern";

   private final Map<String, String> options;
   private final List<String> phases;

   public PhasePatternAction() {
      super(KIND);
      options = new HashMap<>();
      phases = new ArrayList<>();
   }

   public PhasePatternAction(final Map<String, String> options, List<String> phases) {
      super(KIND);
      this.options = options;
      this.phases = phases;
   }

   public Map<String, String> getOptions() { return options; }

   public List<String> getPhases() {
      return phases;
   }
}
