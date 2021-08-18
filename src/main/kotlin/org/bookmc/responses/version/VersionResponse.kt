package org.bookmc.responses.version

data class VersionResponse(val success: Boolean, val latest: String?, val versions: List<String>)