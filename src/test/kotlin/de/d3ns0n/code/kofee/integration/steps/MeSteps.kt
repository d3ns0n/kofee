package de.d3ns0n.code.kofee.integration.steps

import de.d3ns0n.code.kofee.integration.ScenarioContext
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.Matchers.hasItem

class MeSteps(private val scenarioContext: ScenarioContext) {
    @When("^requesting /me\$")
    fun requestingMe() {
        scenarioContext.response = scenarioContext.meClient.get(scenarioContext.jwt)
    }

    @Then("user info response has username {string}")
    fun userInfoResponseHasUsername(username: String) {
        scenarioContext.response.body()
            .jsonPath("$.username").isEqualTo(username)
    }

    @Then("user info response has authority {string}")
    fun userInfoResponseHasAuthority(authority: String) {
        scenarioContext.response.body()
            .jsonPath("$.authorities").value(hasItem(authority))
    }
}
