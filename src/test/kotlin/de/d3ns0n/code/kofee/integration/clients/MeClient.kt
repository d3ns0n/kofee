package de.d3ns0n.code.kofee.integration.clients

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
class MeClient : AbstractClient() {
    fun get(): ClientResponse {
        var response =
            client
                .get()
                .uri { it.path("me").build() }
                // .header(AUTHORIZATION, bearerToken)
                .exchange()
        return ClientResponse(response)
    }
}
