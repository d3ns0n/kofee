package de.d3ns0n.code.kofee.bdd

import com.fasterxml.jackson.databind.ObjectMapper
import de.d3ns0n.code.kofee.bdd.clients.ClientResponse
import de.d3ns0n.code.kofee.bdd.clients.UserInfoClient
import io.cucumber.spring.ScenarioScope
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@ScenarioScope
@Component
data class ScenarioContext(
    val userInfoClient: UserInfoClient,
    val objectMapper: ObjectMapper,
) {
    var jwt: Jwt? = null
    lateinit var response: ClientResponse
}
