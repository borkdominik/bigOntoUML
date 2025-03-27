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
import { PhasePatternAction } from '@borkdominik-biguml/uml-protocol';
import { inject, injectable } from 'inversify';
import * as vscode from 'vscode';

@injectable()
export class PhasePartitionCommand implements VSCodeCommand {
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
        return 'bigUML.phasePartitionPattern';
    }

    execute(): void {
        this.activate();
    }

    async activate(): Promise<void> {
        const idProvider =
            (await vscode.window.showInputBox({
                prompt: 'Enter the Id Provider Class',
                placeHolder: 'Person',
                validateInput: text => (text.trim().length === 0 ? 'Id provider is required' : null)
            })) ?? '';

        const phaseAmount = Number(
            await vscode.window.showInputBox({
                prompt: 'Enter phase amount',
                placeHolder: '2',
                validateInput: text =>
                    Number(text) <= 0
                        ? 'Phase amount needs to be an integer greater than 0' + text + !Number.isInteger(text) + '' + (Number(text) <= 0)
                        : null
            })
        );

        const phases = [];

        for (let i = 0; i < phaseAmount; i++) {
            phases.push(
                (await vscode.window.showInputBox({
                    prompt: 'Enter phase name',
                    placeHolder: 'Married',
                    validateInput: text => (text.trim().length === 0 ? 'Id provider is required' : null)
                })) ?? ''
            );
        }

        this.connector.dispatchAction(PhasePatternAction.create(idProvider, phases));
    }
}
