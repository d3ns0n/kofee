package de.d3ns0n.code.kofee.infrastructure.adapter.incoming

import org.springframework.security.oauth2.core.oidc.StandardClaimNames.PREFERRED_USERNAME
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

// @PreAuthorize("hasRole('customer')")
@RestController
class UserInfoController {
    @GetMapping("/me")
    fun me(auth: JwtAuthenticationToken) =
        Mono.just(
            UserInfoDto(
                auth.token.getClaimAsString(PREFERRED_USERNAME),
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
