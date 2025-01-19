package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.bdd.ScenarioContext
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.springframework.security.oauth2.core.oidc.StandardClaimNames.PREFERRED_USERNAME
import org.springframework.security.oauth2.jwt.Jwt

data class CommonSteps(val scenarioContext: ScenarioContext) {
    @Given("logged in as a customer")
    fun customerJWT() {
        scenarioContext.jwt =
            Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim(PREFERRED_USERNAME, "John Doe")
                .claim("realm_access", mapOf("roles" to listOf("customer")))
                .build()
    }

    @Given("logged in as a coffee farmer")
    fun farmerJWT() {
        scenarioContext.jwt =
            Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim(PREFERRED_USERNAME, "Jane Doe")
                .claim("realm_access", mapOf("roles" to listOf("customer", "coffee_farmer")))
                .build()
    }

    @Then("HTTP status is 200 Ok")
    fun httpStatusIsOk() {
        scenarioContext.response.status.isOk()
    }

    @Then("HTTP status is 401 Unauthorized")
    fun httpStatusIsUnauthorized() {
        scenarioContext.response.status.isUnauthorized()
    }

    @Then("HTTP status is 403 Forbidden")
    fun httpStatusIsForbidden() {
        scenarioContext.response.status.isForbidden()
    }
}
