/*********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 *********************************************************************************/

import { RefreshPropertyPaletteAction, SetPropertyPaletteAction } from '@borkdominik-biguml/uml-protocol';
import { injectable, postConstruct } from 'inversify';
import { VSCodeSettings } from '../../language';
import { getBundleUri, getUri } from '../../utilities/webview';
import { ProviderWebviewContext, UMLWebviewProvider } from '../../vscode/webview/webview-provider';

@injectable()
export class PropertyPaletteProvider extends UMLWebviewProvider {
    get id(): string {
        return VSCodeSettings.propertyPalette.viewId;
    }

    protected override retainContextWhenHidden = true;

    @postConstruct()
    override initialize(): void {
        super.initialize();
        this.connector.onDidActiveClientChange(client => {
            this.connector.sendActionToClient(client.clientId, RefreshPropertyPaletteAction.create());
        });

        this.connector.onDidClientViewStateChange(() => {
            setTimeout(() => {
                if (this.connector.clients.every(c => !c.webviewEndpoint.webviewPanel.active)) {
                    this.sendActionToWebview(SetPropertyPaletteAction.create());
                }
            }, 100);
        });
        this.connector.onDidClientDispose(() => {
            if (this.connector.documents.length === 0) {
                this.sendActionToWebview(SetPropertyPaletteAction.create());
            }
        });
        this.connector.onActionMessage(message => {
            const { action } = message;
            if (SetPropertyPaletteAction.is(action)) {
                this.sendActionToWebview(action);
            }
        });
    }

    protected resolveHTML(providerContext: ProviderWebviewContext): void {
        const webview = providerContext.webviewView.webview;
        const extensionUri = this.extension.extensionUri;

        const codiconsCSSUri = getUri(webview, extensionUri, ['node_modules', '@vscode/codicons', 'dist', 'codicon.css']);
        const bundleJSUri = getBundleUri(webview, extensionUri, ['property-palette', 'bundle.js']);

        webview.html = `<!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="Content-Security-Policy" 
                content="default-src http://*.fontawesome.com  ${webview.cspSource} data: 'unsafe-inline' 'unsafe-eval';">
            <link id="codicon-css" href="${codiconsCSSUri}" rel="stylesheet" />
            <title>Property Palette</title>
        </head>
        <body>
            <big-property-palette-webview></big-property-palette-webview>
            <script type="module" src="${bundleJSUri}"></script>
        </body>
        </html>`;
    }
}
