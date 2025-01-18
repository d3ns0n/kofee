package de.d3ns0n.code.kofee.bdd.clients

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Lazy
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Lazy
@Component
class ItemsClient(context: ApplicationContext) : AbstractClient(context) {
    fun get(jwt: Jwt?): ClientResponse {
        return ClientResponse(
            client
                .withJwt(jwt)
                .get()
                .uri("/items")
                .exchange(),
        )
    }
}
