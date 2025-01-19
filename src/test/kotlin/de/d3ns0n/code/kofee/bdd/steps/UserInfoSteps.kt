package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.application.port.incoming.users.dto.UserInfoResponse
import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.clients.transformTo
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat

class UserInfoSteps(private val scenarioContext: ScenarioContext) {
    @When("requesting the user info endpoint")
    fun requestingUserInfoEndpoint() {
        scenarioContext.response = scenarioContext.userInfoClient.get(scenarioContext.jwt)
    }

    @Then("user info response has username {string}")
    fun userInfoResponseHasUsername(username: String) {
        val userInfo = scenarioContext.response.body.transformTo(UserInfoResponse::class)
        assertThat(userInfo.username).isEqualTo(username)
    }

    @Then("user info response contains authority {string}")
    fun userInfoResponseContainsAuthority(authority: String) {
        val userInfo = scenarioContext.response.body.transformTo(UserInfoResponse::class)
        assertThat(userInfo.authorities).contains(authority)
    }
}
