package org.bookmc.util.version

import org.bookmc.indexer.impl.maven.MavenDatabase
import org.bookmc.indexer.impl.maven.resolve.MavenDataKey
import org.bookmc.util.version.data.IndexedArtifact
import java.net.URL

private const val MAVEN_REPO = "https://maven.bookmc.org/releases"

val indexer = MavenDatabase(URL(MAVEN_REPO))
private var created = false

suspend fun index(artifact: String, classifier: String? = null): List<IndexedArtifact> {
    if (!created) {
        indexer.create()
        created = true
    }

    val group = if (artifact.indexOf('.') != -1) {
        artifact.substringBeforeLast('.').replace(".", "/")
    } else {
        "org.bookmc".replace(".", "/")
    }

    val artifactName = if (artifact.indexOf('.') != -1) artifact.substringAfterLast('.') else artifact

    val key = MavenDataKey(group, artifactName)

    val metadata = indexer.get(key)

    return metadata.metadata.versioning.versions.versions.map {
        IndexedArtifact(
            it,
            MAVEN_REPO + "/${group}/${artifactName}/$it/$artifactName-$it${classifier?.let { c -> "-$c" } ?: ""}.jar"
        )
    }
}