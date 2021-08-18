package org.bookmc.util.version

import io.ktor.client.request.*
import org.bookmc.util.exception.UnknownArtifactException
import org.bookmc.util.http
import org.bookmc.util.version.data.IndexedArtifact
import org.bookmc.util.version.data.MavenAPIResponse

private const val API_URL = "https://maven.bookmc.org/api/releases"
private const val MAVEN_REPO = "https://maven.bookmc.org/releases"

suspend fun index(artifact: String): List<IndexedArtifact> {
    val group = if (artifact.indexOf('.') != -1) {
        artifact.substringBeforeLast('.').replace(".", "/")
    } else {
        "org.bookmc".replace(".", "/")
    }

    return runCatching {
        http.get<MavenAPIResponse>("$API_URL/$group/$artifact")
            .files
            .filter { it.type == "directory" && !it.name.contains("+jdk") }
            .map { IndexedArtifact(it.name, "$MAVEN_REPO/$group/$artifact/${it.name}/$artifact-${it.name}.jar") }
    }.getOrElse { throw UnknownArtifactException("$group.$artifact") }
}