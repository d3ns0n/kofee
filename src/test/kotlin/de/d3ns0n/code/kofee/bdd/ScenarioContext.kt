package de.d3ns0n.code.kofee.bdd

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.d3ns0n.code.kofee.bdd.clients.ClientResponse
import de.d3ns0n.code.kofee.bdd.clients.ItemsClient
import de.d3ns0n.code.kofee.bdd.clients.UserInfoClient
import de.d3ns0n.code.kofee.bdd.consumer.ItemEventTestConsumer
import io.cucumber.spring.ScenarioScope
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.StatusAssertions

@ScenarioScope
@Component
class ScenarioContext(
    val userInfoClient: UserInfoClient,
    val itemsClient: ItemsClient,
    val itemEventTestConsumer: ItemEventTestConsumer,
) {
    var jwt: Jwt? = null
    lateinit var response: ClientResponse

    fun responseStatus(): StatusAssertions {
        return response.status
    }
}

inline fun <reified T> ScenarioContext.responseBody(): T {
    return jacksonObjectMapper().readValue(response.body.returnResult().responseBody, object : TypeReference<T>() {})
}
