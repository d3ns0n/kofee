package de.d3ns0n.code.kofee.integration.clients

import org.springframework.context.ApplicationContext
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity
import org.springframework.test.web.reactive.server.StatusAssertions
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec

abstract class AbstractClient(val context: ApplicationContext) {
    protected var client =
        WebTestClient
            .bindToApplicationContext(this.context)
            .apply(springSecurity())
            .configureClient()
            .build()

    protected fun withJwt() {
        client = client.mutateWith(mockJwt())
    }
}

data class ClientResponse(val responseSpec: WebTestClient.ResponseSpec) {
    fun body(): BodyContentSpec = lazy { responseSpec.expectBody() }.value

    fun status(): StatusAssertions = responseSpec.expectStatus()
}
