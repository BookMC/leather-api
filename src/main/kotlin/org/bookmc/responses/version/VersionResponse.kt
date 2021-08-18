package org.bookmc.responses.version

data class VersionResponse(val latest: String?, val versions: List<String>)