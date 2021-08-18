package org.bookmc.util.exception

class UnknownArtifactException(name: String) : Exception("The artifact, $name, could not be found!")