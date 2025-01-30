package de.d3ns0n.code.kofee.bdd.steps

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.bdd.ScenarioContext
import de.d3ns0n.code.kofee.bdd.responseBody
import de.d3ns0n.code.kofee.item.v1.EventType
import de.d3ns0n.code.kofee.item.v1.ItemEvent
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.kotest.matchers.properties.shouldHaveValue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

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
        scenarioContext.responseBody<ItemResponse>()::id shouldNotBe null
    }

    @Then("create item response has the name {string}")
    fun responseHasName(name: String) {
        scenarioContext.responseBody<ItemResponse>()::name shouldHaveValue name
    }

    @Then("create item response has the price {double}")
    fun responseHasPrice(price: Double) {
        scenarioContext.responseBody<ItemResponse>()::price shouldHaveValue price
    }

    @Then("create item event was sent")
    fun createItemEventWasSent() {
        getFirstItemEvent().eventType shouldBe EventType.CREATE
    }

    @Then("create item event has the name {string}")
    fun createItemEventHasName(name: String) {
        getFirstItemEvent().name shouldBe name
    }

    @Then("create item event has the price {double}")
    fun createItemEventHasPrice(price: Double) {
        getFirstItemEvent().price shouldBe price
    }

    @Then("create item event has publishedAt")
    fun createItemEventHasPublishedAt() {
        getFirstItemEvent().publishedAt shouldNotBe null
    }

    private fun getFirstItemEvent(): ItemEvent {
        val id = scenarioContext.responseBody<ItemResponse>().id
        return scenarioContext.itemEventTestConsumer.getItemEvents(id!!).first()
    }
}
