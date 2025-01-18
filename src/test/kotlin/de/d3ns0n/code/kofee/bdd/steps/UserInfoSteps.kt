package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.clients.transformTo
import de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest.UserInfoController.UserInfoDto
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat

class UserInfoSteps(private val scenarioContext: ScenarioContext) {
    @When("^requesting /me\$")
    fun requestingMe() {
        scenarioContext.response = scenarioContext.userInfoClient.get(scenarioContext.jwt)
    }

    @Then("user info response has username {string}")
    fun userInfoResponseHasUsername(username: String) {
        val userInfo = scenarioContext.response.body.transformTo(UserInfoDto::class)
        assertThat(userInfo.username).isEqualTo(username)
    }

    @Then("user info response contains authority {string}")
    fun userInfoResponseContainsAuthority(authority: String) {
        val userInfo = scenarioContext.response.body.transformTo(UserInfoDto::class)
        assertThat(userInfo.authorities).contains(authority)
    }
}
