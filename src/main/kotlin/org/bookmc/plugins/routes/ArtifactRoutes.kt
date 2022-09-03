package org.bookmc.plugins.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bookmc.plugins.routes.responses.version.VersionResponse
import org.bookmc.util.version.index

fun Route.artifact() {
    get("/versions") {
        val artifact = call.parameters["artifact"]!!
        val versions = index(artifact, call.request.queryParameters["classifier"])
        call.respond(VersionResponse(true, versions.latest, versions.artifacts))
    }
}