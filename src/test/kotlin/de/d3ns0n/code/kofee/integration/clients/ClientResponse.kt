package de.d3ns0n.code.kofee.integration.clients

import org.springframework.test.web.reactive.server.StatusAssertions
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec

data class ClientResponse(val responseSpec: WebTestClient.ResponseSpec) {
    fun body(): BodyContentSpec = lazy { responseSpec.expectBody() }.value

    fun status(): StatusAssertions = responseSpec.expectStatus()
}
