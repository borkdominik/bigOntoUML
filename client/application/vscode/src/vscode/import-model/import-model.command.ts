/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { type BIGGLSPVSCodeConnector, TYPES, type VSCodeCommand } from '@borkdominik-biguml/big-vscode-integration/vscode';
import { type IDESessionClient } from '@borkdominik-biguml/big-vscode-integration/vscode-node';
import { type UMLDiagramEditorProvider } from '@borkdominik-biguml/uml-glsp-client/vscode';
import { ImportModelAction } from '@borkdominik-biguml/uml-protocol';
import { inject, injectable } from 'inversify';
import URIJS from 'urijs';
import * as vscode from 'vscode';

@injectable()
export class ImportModelCommand implements VSCodeCommand {
    @inject(TYPES.GLSPVSCodeConnector)
    protected readonly connector: BIGGLSPVSCodeConnector;

    constructor(
        @inject(TYPES.IDESessionClient)
        protected readonly session: IDESessionClient,
        @inject(TYPES.EditorProvider)
        protected readonly editor: UMLDiagramEditorProvider,
        @inject(TYPES.ExtensionContext)
        protected readonly context: vscode.ExtensionContext
    ) {}

    get id(): string {
        return 'bigUML.importModel';
    }

    execute(): void {
        this.activate();
    }

    async activate(): Promise<void> {
        // Step 1: Ask for the filename
        const uri = await vscode.window.showInputBox({
            prompt: 'Enter the file path to import',
            placeHolder: 'example.txt',
            validateInput: text => (text.trim().length === 0 ? 'File path cannot be empty' : null)
        });

        if (!uri) {
            vscode.window.showErrorMessage('File path is required.');
            return;
        }

        // Step 2: Ask for a name
        const name = await vscode.window.showInputBox({
            prompt: 'Enter model name',
            placeHolder: 'OntoModel',
            validateInput: text => (text.trim().length === 0 ? 'Name cannot be empty' : null)
        });

        if (!name) {
            vscode.window.showErrorMessage('Name is required.');
            return;
        }

        const workspaces = vscode.workspace.workspaceFolders;
        const workspace = workspaces?.[0];

        const modelUri = new URIJS(workspace?.uri + '/' + name).path();

        const client = await this.session.client();
        client.sendActionMessage({
            action: ImportModelAction.importModel(uri, name, modelUri),
            clientId: client.id
        });
    }
}
