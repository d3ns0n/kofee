package de.d3ns0n.code.kofee.integration

import de.d3ns0n.code.kofee.integration.clients.ClientResponse
import de.d3ns0n.code.kofee.integration.clients.MeClient
import io.cucumber.spring.ScenarioScope
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@ScenarioScope
@Component
data class ScenarioContext(
    val meClient: MeClient,
) {
    var jwt: Jwt? = null
    lateinit var response: ClientResponse
}
