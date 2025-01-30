package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.application.port.incoming.users.dto.UserInfoResponse
import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.responseBody
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.properties.shouldHaveValue

class UserInfoSteps(private val scenarioContext: ScenarioContext) {
    @When("requesting the user info endpoint")
    fun requestingUserInfoEndpoint() {
        val clientResponse = scenarioContext.userInfoClient.get(scenarioContext.jwt)
        scenarioContext.response = clientResponse
    }

    @Then("user info response has username {string}")
    fun userInfoResponseHasUsername(username: String) {
        scenarioContext.responseBody<UserInfoResponse>()::username shouldHaveValue username
    }

    @Then("user info response contains authority {string}")
    fun userInfoResponseContainsAuthority(authority: String) {
        scenarioContext.responseBody<UserInfoResponse>().authorities shouldContain authority
    }
}
