package org.bookmc.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import org.bookmc.util.exception.UnknownArtifactException
import org.bookmc.util.exception.UnknownVersionException
import org.bookmc.plugins.routes.responses.GenericResponse

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<UnknownVersionException> {
            call.respond(HttpStatusCode.NotFound, GenericResponse(false, it.message!!))
        }
        exception<UnknownArtifactException> {
            call.respond(HttpStatusCode.NotFound, GenericResponse(false, it.message))
        }
        exception<Throwable> {
            call.respond(HttpStatusCode.InternalServerError, GenericResponse(false, it.message))
        }

        status(HttpStatusCode.NotFound) {
            call.respond(GenericResponse(false, "Unknown path"))
        }
    }
}