package de.d3ns0n.code.kofee.bdd

import de.d3ns0n.code.kofee.bdd.clients.ClientResponse
import de.d3ns0n.code.kofee.bdd.clients.MeClient
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
