package de.d3ns0n.code.ko_fee.users

import org.springframework.security.oauth2.core.oidc.StandardClaimNames
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MeController {

    @GetMapping("/me")
    fun getGreeting(auth: JwtAuthenticationToken): UserInfoDto {
        return UserInfoDto(
            auth.token.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
            auth.authorities.map { it.authority })
    }

    data class UserInfoDto(val name: String, val roles: List<String>)
}