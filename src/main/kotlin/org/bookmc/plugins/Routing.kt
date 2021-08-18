package org.bookmc.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bookmc.plugins.routes.artifact
import org.bookmc.plugins.routes.mappings
import org.bookmc.plugins.routes.responses.GenericResponse
import org.bookmc.plugins.routes.responses.version.VersionResponse
import org.bookmc.util.version.index

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(GenericResponse(true, null))
        }

        // v1
        route("/v1") {
            route("/mappings") {
                mappings()
            }
            route("/artifact") {
                artifact()
            }
        }
    }
}