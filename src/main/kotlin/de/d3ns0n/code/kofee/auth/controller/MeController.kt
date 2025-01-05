package de.d3ns0n.code.kofee.auth.controller

import org.springframework.security.oauth2.core.oidc.StandardClaimNames.PREFERRED_USERNAME
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MeController {
    @GetMapping("/me")
    fun me(auth: JwtAuthenticationToken) =
        UserInfoDto(
            auth.token.getClaimAsString(PREFERRED_USERNAME),
            auth.authorities.map { it.authority },
            auth.tokenAttributes,
        )

    data class UserInfoDto(
        val username: String,
        val authorities: List<String>,
        val tokenAttributes: Map<String, Any>,
    )
}
