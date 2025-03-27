/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { type BIGGLSPVSCodeConnector, TYPES, type VSCodeCommand } from '@borkdominik-biguml/big-vscode-integration/vscode';
import { ExportModelAction } from '@borkdominik-biguml/uml-protocol';
import { inject, injectable } from 'inversify';
import * as vscode from 'vscode';

@injectable()
export class ExportModelCommand implements VSCodeCommand {
    @inject(TYPES.GLSPVSCodeConnector)
    protected readonly connector: BIGGLSPVSCodeConnector;

    get id(): string {
        return 'bigUML.exportModel';
    }

    execute(): void {
        this._execute();
    }
    private async _execute(): Promise<void> {
        const workspaces = vscode.workspace.workspaceFolders;
        const filePath = workspaces?.[0]?.uri.path + '/ontology.json';
        this.connector.sendActionToActiveClient(ExportModelAction.exportModel(filePath));
    }
}
