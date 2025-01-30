package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.responseBody
import de.d3ns0n.code.kofee.item.v1.EventType
import de.d3ns0n.code.kofee.item.v1.ItemEvent
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
        val response = scenarioContext.responseBody<ItemResponse>()
        assertThat(response.id).isNotNull
    }

    @Then("create item response has the name {string}")
    fun responseHasName(name: String) {
        val response = scenarioContext.responseBody<ItemResponse>()
        assertThat(response.name).isEqualTo(name)
    }

    @Then("create item response has the price {double}")
    fun responseHasPrice(price: Double) {
        val response = scenarioContext.responseBody<ItemResponse>()
        assertThat(response.price).isEqualTo(price)
    }

    @Then("create item event was sent")
    fun createItemEventWasSent() {
        val itemEvent = getFirstItemEvent()
        assertThat(itemEvent.eventType).isEqualTo(EventType.CREATE)
    }

    @Then("create item event has the name {string}")
    fun createItemEventHasName(name: String) {
        val itemEvent = getFirstItemEvent()
        assertThat(itemEvent.name).isEqualTo(name)
    }

    @Then("create item event has the price {double}")
    fun createItemEventHasPrice(price: Double) {
        val itemEvent = getFirstItemEvent()
        assertThat(itemEvent.price).isEqualTo(price)
    }

    @Then("create item event has publishedAt")
    fun createItemEventHasPublishedAt() {
        val itemEvent = getFirstItemEvent()
        assertThat(itemEvent.publishedAt).isNotNull
    }

    private fun getFirstItemEvent(): ItemEvent {
        val id = scenarioContext.responseBody<ItemResponse>().id
        return scenarioContext.itemEventTestConsumer.getItemEvents(id!!).first()
    }
}
