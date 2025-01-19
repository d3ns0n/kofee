package de.d3ns0n.code.kofee.application.port.incoming.users.dto

data class UserInfoResponse(
    val username: String,
    val authorities: List<String>,
    val tokenAttributes: Map<String, Any>,
    val tokenValue: String,
)
