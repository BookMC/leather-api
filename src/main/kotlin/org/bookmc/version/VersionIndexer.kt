package org.bookmc.version

import io.ktor.client.request.*
import org.bookmc.util.http
import org.bookmc.version.data.MavenAPIResponse

private const val API_URL = "https://maven.bookmc.org/api/releases/"

suspend fun index(artifact: String, group: String = "org.bookmc"): List<String> {
    return http.get<MavenAPIResponse>("$API_URL${group.replace(".", "/")}/$artifact")
        .files
        .filter { it.type == "directory" && !it.name.contains("+jdk") }
        .map { it.name }
}