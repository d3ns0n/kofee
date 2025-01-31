package de.d3ns0n.code.kofee.bdd.clients

import de.d3ns0n.code.kofee.infrastructure.configuration.SecurityConfiguration.Companion.authoritiesFromJwtRealmRoles
import org.springframework.context.ApplicationContext
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity
import org.springframework.test.web.reactive.server.StatusAssertions
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec

abstract class AbstractClient(context: ApplicationContext) {
    protected val client: WebTestClient =
        WebTestClient
            .bindToApplicationContext(context)
            .apply(springSecurity())
            .configureClient()
            .build()
}

class ClientResponse(private val responseSpec: WebTestClient.ResponseSpec) {
    val status: StatusAssertions = responseSpec.expectStatus()
    val body: BodyContentSpec = lazy { responseSpec.expectBody() }.value
}

/**
 * Will return a new [WebTestClient] instance with the [Jwt] and correct authoritiesConverter will be returned, if [jwt] is not null
 */
fun WebTestClient.withJwt(jwt: Jwt?): WebTestClient {
    if (jwt == null) return this

    return this.mutateWith(
        mockJwt().jwt(jwt).authorities(
            Converter<Jwt, Collection<GrantedAuthority>> {
                authoritiesFromJwtRealmRoles(it)
            },
        ),
    )
}
