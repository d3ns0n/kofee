package de.d3ns0n.code.kofee.bdd.consumer

import de.d3ns0n.code.kofee.bdd.INTEGRATION_TEST_PROFILE
import de.d3ns0n.code.kofee.item.v1.ItemEvent
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Profile(INTEGRATION_TEST_PROFILE)
class ItemEventTestConsumer {
    private val events: MutableMap<UUID, MutableList<ItemEvent>> = mutableMapOf()

    @KafkaListener(
        id = "\${spring.kafka.consumer.client-id}-ItemEventTestConsumer",
        topics = ["\${spring.kafka.topics.items}"],
    )
    fun consumeItemEvent(itemEvent: ItemEvent) {
        println("Consumed event $itemEvent")
        events.getOrPut(itemEvent.id) { mutableListOf() }.add(itemEvent)
    }

    fun getItemEvents(id: UUID): List<ItemEvent> = events[id].orEmpty()
}
