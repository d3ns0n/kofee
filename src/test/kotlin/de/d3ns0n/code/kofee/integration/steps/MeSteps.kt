package de.d3ns0n.code.kofee.integration.steps

import de.d3ns0n.code.kofee.integration.ScenarioContext
import io.cucumber.java.en.When

class MeSteps(private val scenarioContext: ScenarioContext) {
    @When("^requesting /me\$")
    fun requestingMe() {
        scenarioContext.response = scenarioContext.meClient.get()
    }
}
