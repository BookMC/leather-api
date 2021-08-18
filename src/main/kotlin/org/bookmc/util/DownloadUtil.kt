package org.bookmc.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bookmc.util.version.data.IndexedArtifact
import java.io.File
import java.net.URL

private const val MAVEN_LOCATION = "https://maven.bookmc.org/releases/org/bookmc/leather/%s/leather-%s.jar"

suspend fun download(version: IndexedArtifact): File {
    val file = File("./mappings/download/${version.version}.jar").apply {
        parentFile?.mkdirs()
        withContext(Dispatchers.IO) {
            createNewFile()
        }
        deleteOnExit()
    }

    withContext(Dispatchers.IO) {
        URL(version.url).openStream().use {
            file.outputStream().use { fos ->
                it.copyTo(fos)
            }
        }
    }

    return file
}