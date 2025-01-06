package de.d3ns0n.code.kofee.integration

import com.github.tomakehurst.wiremock.WireMockServer
import de.d3ns0n.code.kofee.integration.clients.ClientResponse
import de.d3ns0n.code.kofee.integration.clients.MeClient
import io.cucumber.spring.ScenarioScope
import org.springframework.stereotype.Component

@ScenarioScope
@Component
data class ScenarioContext(
    val wireMockServer: WireMockServer,
    val meClient: MeClient,
) {
    lateinit var response: ClientResponse
}
