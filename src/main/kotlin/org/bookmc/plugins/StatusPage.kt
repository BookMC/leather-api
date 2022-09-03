package org.bookmc.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.bookmc.util.exception.UnknownArtifactException
import org.bookmc.util.exception.UnknownVersionException
import org.bookmc.plugins.routes.responses.GenericResponse

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception(UnknownVersionException::class) { call, ex ->
            call.respond(HttpStatusCode.NotFound, GenericResponse(false, ex.message!!))
        }
        exception(UnknownArtifactException::class) { call, ex ->
            call.respond(HttpStatusCode.NotFound, GenericResponse(false, ex.message))
        }
        exception(Throwable::class) { call, ex ->
            call.respond(HttpStatusCode.InternalServerError, GenericResponse(false, ex.message))
        }

        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(GenericResponse(false, "Unknown path"))
        }
    }
}