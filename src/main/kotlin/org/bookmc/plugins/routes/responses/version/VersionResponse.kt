package org.bookmc.plugins.routes.responses.version

import org.bookmc.util.version.data.IndexedArtifact

data class VersionResponse(val success: Boolean, val latest: IndexedArtifact?, val versions: List<IndexedArtifact>)