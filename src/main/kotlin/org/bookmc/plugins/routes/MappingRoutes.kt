package org.bookmc.plugins.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bookmc.plugins.routes.responses.version.VersionResponse
import org.bookmc.util.download
import org.bookmc.util.exception.UnknownVersionException
import org.bookmc.util.version.data.IndexedArtifacts
import org.bookmc.util.version.index

fun Route.mappings() {
    route("/{mcversion}") {
        get {
            val minecraftVersion = call.parameters["mcversion"]!!

            val versions = index("leather")
                .artifacts
                .filter { it.version.endsWith("-$minecraftVersion") }
                .map { IndexedArtifacts.IndexedArtifact(it.version.removeSuffix("-$minecraftVersion"), it.url) }

            call.respond(VersionResponse(true, versions.firstOrNull(), versions))
        }


        get("/{version}/download") {
            val mcVersion = call.parameters["mcversion"]!!
            val version = call.parameters["version"]!!

            val builtVersion = "$version-$mcVersion"

            val versions = index("leather")

            val indexedVersion = version.takeIf { it == "latest" }
                ?.let { versions.artifacts.firstOrNull { it.version.endsWith("-$mcVersion") } }
                ?: versions.artifacts.find { it.version == builtVersion }
                ?: throw UnknownVersionException(builtVersion)
            val file = download(indexedVersion)

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment
                    .withParameter(ContentDisposition.Parameters.FileName, file.name)
                    .toString()
            )
            call.respondFile(file)
        }
    }
}