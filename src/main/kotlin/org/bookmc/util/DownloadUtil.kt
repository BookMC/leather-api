package org.bookmc.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bookmc.util.version.data.IndexedArtifacts
import java.io.File
import java.net.URL

suspend fun download(version: IndexedArtifacts.IndexedArtifact): File {
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