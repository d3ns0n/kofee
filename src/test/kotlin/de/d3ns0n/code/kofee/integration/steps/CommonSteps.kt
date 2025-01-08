package de.d3ns0n.code.kofee.integration.steps

import de.d3ns0n.code.kofee.integration.ScenarioContext
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.springframework.security.oauth2.core.oidc.StandardClaimNames.PREFERRED_USERNAME
import org.springframework.security.oauth2.core.oidc.StandardClaimNames.SUB
import org.springframework.security.oauth2.jwt.Jwt

data class CommonSteps(val scenarioContext: ScenarioContext) {
    @Given("a valid JWT")
    fun validJWT() {
        scenarioContext.jwt =
            Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim(PREFERRED_USERNAME, "John Doe")
                .claim(SUB, "user")
                .claim("scope", listOf("read", "write"))
                .build()
    }

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
