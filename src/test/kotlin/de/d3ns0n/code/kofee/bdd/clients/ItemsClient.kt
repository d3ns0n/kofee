package de.d3ns0n.code.kofee.bdd.clients

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Lazy
import org.springframework.http.MediaType
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

private const val URI = "/items"

@Lazy
@Component
class ItemsClient(context: ApplicationContext) : AbstractClient(context) {
    fun get(jwt: Jwt?): ClientResponse {
        return ClientResponse(
            client
                .withJwt(jwt)
                .get()
                .uri(URI)
                .exchange(),
        )
    }

    fun post(
        jwt: Jwt?,
        createItemRequest: CreateItemRequest,
    ): ClientResponse {
        return ClientResponse(
            client
                .withJwt(jwt)
                .post()
                .uri(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createItemRequest)
                .exchange(),
        )
    }
}
