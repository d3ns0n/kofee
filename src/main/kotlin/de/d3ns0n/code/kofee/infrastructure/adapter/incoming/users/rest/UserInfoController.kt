package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.users.rest

import de.d3ns0n.code.kofee.application.port.incoming.users.GetUserInfo
import de.d3ns0n.code.kofee.application.port.incoming.users.dto.UserInfoResponse
import org.springframework.security.oauth2.core.oidc.StandardClaimNames
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserInfoController : GetUserInfo {
    @GetMapping("/me")
    override fun getUserInfo(jwt: JwtAuthenticationToken): Mono<UserInfoResponse> =
        Mono.just(
            UserInfoResponse(
                jwt.token.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                jwt.authorities.map { it.authority },
                jwt.tokenAttributes,
                jwt.token.tokenValue,
            ),
        )
}
