package de.d3ns0n.code.kofee.application.port.incoming.users

import de.d3ns0n.code.kofee.application.port.incoming.users.dto.UserInfoResponse
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import reactor.core.publisher.Mono

interface GetUserInfo {
    fun getUserInfo(jwt: JwtAuthenticationToken): Mono<UserInfoResponse>
}
