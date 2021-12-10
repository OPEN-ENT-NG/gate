/*
 * Copyright © Nouvelle-Calédonie Province Sud, 2021.
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

package fr.openent.gate.services.impl;

import fr.wseduc.webutils.Either;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.entcore.common.neo4j.Neo4j;
import org.entcore.common.neo4j.Neo4jResult;

public class DefaultGateService implements fr.openent.gate.services.GateService {

    private final Neo4j neo4j = Neo4j.getInstance();

    @Override
    public void structures(Handler<Either<String, JsonArray>> handler) {
        final JsonObject params = new JsonObject();
        String query =
                "MATCH (s:Structure) " +
                        "OPTIONAL MATCH (s)-[r:HAS_ATTACHMENT]->(ps:Structure) " +
                        "WITH s, COLLECT({id: ps.id, name: ps.name}) as parents " +
                        "RETURN s.id as id, s.UAI as UAI, s.name as name, s.externalId as externalId, s.timetable as timetable, s.punctualTimetable AS punctualTimetable, " +
                        "s.hasApp as hasApp, s.levelsOfEducation as levelsOfEducation, s.distributions as distributions, s.manualName AS manualName, " +
                        "s.feederName AS feederName, s.source AS source, " +
                        "CASE WHEN any(p in parents where p <> {id: null, name: null}) THEN parents END as parents";

        neo4j.execute(query, params, Neo4jResult.validResultHandler(handler));
    }

}
