package com.borkdominik.big.glsp.uml.core.pattern;

import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.server.internal.gmodel.commandstack.GModelRecordingCommand;

public class PhaseCommand  extends GModelRecordingCommand {

    public PhaseCommand(GModelRoot root, String label, Runnable runnable) {
        super(root, label, runnable);
    }
}
