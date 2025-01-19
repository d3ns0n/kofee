package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.clients.transformTo
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat

class CreateItemSteps(private val scenarioContext: ScenarioContext) {
    @When("creating an item with name {string} and price {double}")
    fun createItemWithNameAndPrice(
        name: String,
        price: Double,
    ) {
        scenarioContext.response = scenarioContext.itemsClient.post(scenarioContext.jwt, CreateItemRequest(name, price))
    }

    @Then("create item response has an id")
    fun responseHasId() {
        val response = scenarioContext.response.body.transformTo(ItemResponse::class)
        assertThat(response.id).isNotNull
    }

    @Then("create item response has the name {string}")
    fun responseHasName(name: String) {
        val response = scenarioContext.response.body.transformTo(ItemResponse::class)
        assertThat(response.name).isEqualTo(name)
    }

    @Then("create item response has the price {double}")
    fun responseHasPrice(price: Double) {
        val response = scenarioContext.response.body.transformTo(ItemResponse::class)
        assertThat(response.price).isEqualTo(price)
    }
}
