/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/
import { ExportModelAction } from '@borkdominik-biguml/uml-protocol';
import { inject, injectable } from 'inversify';
import * as vscode from 'vscode';
import { TYPES } from '../../di.types';
import { UMLGLSPConnector } from '../../glsp/uml-glsp-connector';
import { VSCodeCommand } from '../command/command';

@injectable()
export class ExportModelCommand implements VSCodeCommand {
    @inject(TYPES.Connector)
    protected readonly connector: UMLGLSPConnector;

    get id(): string {
        return 'bigUML.exportModel';
    }

    execute(...args: any[]): void {
        this._execute();
    }
    private async _execute(): Promise<void> {
        const workspaces = vscode.workspace.workspaceFolders;
        const filePath = workspaces?.[0] + '/ontology.json';
        this.connector.sendActionToActiveClient(ExportModelAction.exportModel(filePath));
    }
}
