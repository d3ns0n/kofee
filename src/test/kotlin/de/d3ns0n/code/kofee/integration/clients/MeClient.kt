package de.d3ns0n.code.kofee.integration.clients

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

private const val URI = "/me"

@Lazy
@Component
class MeClient(context: ApplicationContext) : AbstractClient(context) {
    fun get(): ClientResponse =
        ClientResponse(
            client
                .get()
                .uri(URI)
                .exchange(),
        )
}
