package org.bookmc

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.bookmc.exception.UnknownVersionException
import org.bookmc.plugins.configureRouting
import org.bookmc.plugins.configureSerialization
import org.bookmc.responses.GenericResponse

fun main() {
    System.setProperty("io.ktor.development", "true")

    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toIntOrNull() ?: 8080,
        host = System.getenv("HOST") ?: "0.0.0.0",
        watchPaths = listOf("classes", "resources")
    ) {
        configureRouting()
        configureSerialization()

        install(StatusPages) {
            exception<UnknownVersionException> {
                call.respond(HttpStatusCode.NotFound, GenericResponse(false, it.message!!))
            }
        }
    }.start(wait = true)
}
