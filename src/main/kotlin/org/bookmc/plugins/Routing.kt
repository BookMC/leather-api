package org.bookmc.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bookmc.exception.UnknownVersionException
import org.bookmc.responses.GenericResponse
import org.bookmc.responses.version.VersionResponse
import org.bookmc.util.download
import org.bookmc.version.index

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(GenericResponse(true, null))
        }

        // v1
        route("/v1") {
            get {
                call.respond(GenericResponse(true, null))
            }

            get("/") {
                call.respond(GenericResponse(true, null))
            }

            get("/versions") {
                val versions = index("leather")

                call.respond(VersionResponse(versions.firstOrNull(), versions))
            }

            get("/versions/{mcversion}/{version}/download") {
                val mcVersion = call.parameters["mcversion"]!!
                val version = call.parameters["version"]!!

                val builtVersion = "$version-$mcVersion"

                val versions = index("leather")

                val indexedVersion = version.takeIf { it == "latest" }
                    ?.let { versions.firstOrNull { it.endsWith("-$mcVersion") } }
                    ?: versions.getOrNull(versions.indexOf(builtVersion))
                    ?: throw UnknownVersionException(builtVersion)

                call.respondFile(download(indexedVersion))
            }
        }
    }
}