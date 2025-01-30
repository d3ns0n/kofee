package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.kafka

import de.d3ns0n.code.kofee.application.port.outgoing.ItemPublisher
import de.d3ns0n.code.kofee.domain.Item
import de.d3ns0n.code.kofee.item.v1.EventType
import de.d3ns0n.code.kofee.item.v1.ItemEvent
import org.apache.avro.specific.SpecificRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional("kafkaTransactionManager")
class KafkaItemPublisher(
    @Value("\${spring.kafka.topics.items}")
    private val topic: String,
    private val kafkaTemplate: KafkaTemplate<String, SpecificRecord>,
) : ItemPublisher {
    override fun publish(
        eventType: EventType,
        item: Item,
    ) {
        val itemEvent = mapToItemEvent(eventType, item)
        kafkaTemplate.send(topic, itemEvent.id.toString(), itemEvent)
    }

    private fun mapToItemEvent(
        eventType: EventType,
        item: Item,
    ): ItemEvent =
        ItemEvent(
            item.id,
            eventType,
            item.name,
            item.price,
            Instant.now(),
        )
}
