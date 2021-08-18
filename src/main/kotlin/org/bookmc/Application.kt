package org.bookmc

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.bookmc.plugins.configureRouting
import org.bookmc.plugins.configureSerialization
import org.bookmc.plugins.configureStatusPage

fun main() {
    embeddedServer(
        Netty,
        port = System.getenv("PORT")?.toIntOrNull() ?: 8080,
        host = System.getenv("HOST") ?: "0.0.0.0",
        watchPaths = listOf("classes", "resources")
    ) {
        configureRouting()
        configureSerialization()
        configureStatusPage()
    }.start(wait = true)
}
