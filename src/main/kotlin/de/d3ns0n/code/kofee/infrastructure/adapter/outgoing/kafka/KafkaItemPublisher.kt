package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.kafka

import de.d3ns0n.code.kofee.application.port.outgoing.ItemPublisher
import de.d3ns0n.code.kofee.domain.Item
import de.d3ns0n.code.kofee.item.v1.ItemEvent
import org.apache.avro.specific.SpecificRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class KafkaItemPublisher(private val kafkaTemplate: KafkaTemplate<String, SpecificRecord>) : ItemPublisher {
    private val topic = "items-v1"

    @Transactional("kafkaTransactionManager")
    override fun publish(item: Item) {
        val itemEvent = mapToItemEvent(item)
        kafkaTemplate.send(topic, itemEvent.id.toString(), itemEvent).get()
    }

    fun mapToItemEvent(item: Item): ItemEvent = ItemEvent(item.id, item.name, item.price, Instant.now())
}
