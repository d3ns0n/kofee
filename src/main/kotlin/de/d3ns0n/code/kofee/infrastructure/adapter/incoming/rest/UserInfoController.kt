package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest

import org.springframework.security.oauth2.core.oidc.StandardClaimNames
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/me")
class UserInfoController {
    @GetMapping
    fun me(auth: JwtAuthenticationToken) =
        Mono.just(
            UserInfoDto(
                auth.token.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                auth.authorities.map { it.authority },
                auth.tokenAttributes,
                auth.token.tokenValue,
            ),
        )

    data class UserInfoDto(
        val username: String,
        val authorities: List<String>,
        val tokenAttributes: Map<String, Any>,
        val tokenValue: String,
    )
}
