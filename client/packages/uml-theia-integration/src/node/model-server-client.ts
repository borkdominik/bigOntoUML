/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
import { ModelServerMessage } from '@eclipse-emfcloud/modelserver-client/lib/model-server-message';
import { TheiaBackendModelServerClientV2 } from '@eclipse-emfcloud/modelserver-theia/lib/node';
import { injectable } from 'inversify';
import { AxiosResponse } from 'axios';

import { UmlDiagramType } from '../common/uml-language';

export namespace UmlModelServerPaths {
    export const CREATE_UML = 'uml/create';
}

@injectable()
export class UmlModelServerClient extends TheiaBackendModelServerClientV2 {
    async createUmlResource(modelName: string, diagramType: UmlDiagramType): Promise<AxiosResponse<ModelServerMessage>> {
        const newModelUri = `${modelName}/model/${modelName}.uml`;
        return this.restClient.get(`${UmlModelServerPaths.CREATE_UML}?modeluri=${newModelUri}&diagramtype=${diagramType}`);
    }
}
