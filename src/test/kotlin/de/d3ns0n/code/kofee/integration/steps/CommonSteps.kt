package de.d3ns0n.code.kofee.integration.steps

import de.d3ns0n.code.kofee.integration.ScenarioContext
import io.cucumber.java.en.Then

data class CommonSteps(val scenarioContext: ScenarioContext) {
    @Then("HTTP status is 200 Ok")
    fun httpStatusIsOk() {
        scenarioContext.response.status().isOk()
    }

    @Then("HTTP status is 401 Unauthorized")
    fun httpStatusIsUnauthorized() {
        scenarioContext.response.status().isUnauthorized()
    }

    @Then("HTTP status is 403 Forbidden")
    fun httpStatusIsForbidden() {
        scenarioContext.response.status().isForbidden()
    }
}
