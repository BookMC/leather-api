package org.bookmc.util.exception

class UnknownVersionException(version: String) : IllegalStateException("The version, $version, could not be found.")