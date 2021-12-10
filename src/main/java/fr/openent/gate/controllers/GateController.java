/*
 * Copyright © Nouvelle-Calédonie Province Sud, 2022.
 *
 * This file is part of OPEN ENT NG. OPEN ENT NG is a versatile ENT Project based on the JVM and ENT Core Project.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation (version 3 of the License).
 *
 * For the sake of explanation, any module that communicate over native
 * Web protocols, such as HTTP, with OPEN ENT NG is outside the scope of this
 * license and could be license under its own terms. This is merely considered
 * normal use of OPEN ENT NG, and does not fall under the heading of "covered work".
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

package fr.openent.gate.controllers;

import fr.openent.gate.services.GateService;
import fr.wseduc.rs.Get;
import fr.wseduc.security.ActionType;
import fr.wseduc.security.SecuredAction;
import fr.wseduc.webutils.http.BaseController;
import io.vertx.core.http.HttpServerRequest;

import static org.entcore.common.http.response.DefaultResponseHandler.arrayResponseHandler;

public class GateController extends BaseController {

    private GateService gateService;

    @Get("/structures")
    @SecuredAction(value = "gate.structures", type = ActionType.WORKFLOW)
    public void structures(HttpServerRequest request) {
        gateService.structures(arrayResponseHandler(request));
    }

    public void setGateService(GateService gateService) {
        this.gateService = gateService;
    }

}
