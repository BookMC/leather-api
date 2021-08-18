package org.bookmc.plugins.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bookmc.plugins.routes.responses.version.VersionResponse
import org.bookmc.util.version.index

fun Route.artifact() {
    get("/versions") {
        val artifact = call.parameters["artifact"]!!
        val versions = index(artifact)
        call.respond(VersionResponse(true, versions.firstOrNull(), versions))
    }
}