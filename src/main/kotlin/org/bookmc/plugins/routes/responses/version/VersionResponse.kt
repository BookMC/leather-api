package org.bookmc.plugins.routes.responses.version

import org.bookmc.util.version.data.IndexedArtifacts

data class VersionResponse(val success: Boolean, val latest: IndexedArtifacts.IndexedArtifact?, val versions: List<IndexedArtifacts.IndexedArtifact>)