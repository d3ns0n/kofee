package de.d3ns0n.code.kofee.bdd.clients

import org.springframework.context.ApplicationContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity
import org.springframework.test.web.reactive.server.StatusAssertions
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec

abstract class AbstractClient(context: ApplicationContext) {
    protected var client: WebTestClient =
        WebTestClient
            .bindToApplicationContext(context)
            .apply(springSecurity())
            .configureClient()
            .build()
}

data class ClientResponse(val responseSpec: WebTestClient.ResponseSpec) {
    fun body(): BodyContentSpec = lazy { responseSpec.expectBody() }.value

    fun status(): StatusAssertions = responseSpec.expectStatus()
}

/**
 * If [jwt] is not null, then apply it to the [WebTestClient] and build a new instance
 */
fun WebTestClient.withJwt(jwt: Jwt?): WebTestClient {
    if (jwt == null) return this

    return this.mutateWith(mockJwt().jwt(jwt))
}
