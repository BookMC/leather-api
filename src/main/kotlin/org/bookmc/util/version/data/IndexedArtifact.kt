package org.bookmc.util.version.data

data class IndexedArtifacts(val latest: IndexedArtifact, val artifacts: List<IndexedArtifact>) {
    data class IndexedArtifact(val version: String, val url: String)
}
