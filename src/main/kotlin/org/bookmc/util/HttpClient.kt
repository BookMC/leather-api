package org.bookmc.util

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

val http = HttpClient(Apache) {
    install(ContentNegotiation) {
        gson()
    }
}
