package org.bookmc.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bookmc.plugins.routes.artifact
import org.bookmc.plugins.routes.mappings
import org.bookmc.plugins.routes.responses.GenericResponse

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
            route("/{artifact}") {
                artifact()
            }
        }
    }
}