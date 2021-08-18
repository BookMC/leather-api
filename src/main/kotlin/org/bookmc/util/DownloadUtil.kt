package org.bookmc.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

private const val MAVEN_LOCATION = "https://maven.bookmc.org/releases/org/bookmc/leather/%s/leather-%s.jar"

suspend fun download(version: String): File {
    val url = MAVEN_LOCATION.format(version, version)

    val file = File("./mappings/download/$version.jar").apply {
        parentFile?.mkdirs()
        withContext(Dispatchers.IO) {
            createNewFile()
        }
        deleteOnExit()
    }

    withContext(Dispatchers.IO) {
        URL(url).openStream().use {
            file.outputStream().use { fos ->
                it.copyTo(fos)
            }
        }
    }

    return file
}