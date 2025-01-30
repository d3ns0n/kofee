package de.d3ns0n.code.kofee.application.port.outgoing

import de.d3ns0n.code.kofee.domain.Item
import de.d3ns0n.code.kofee.item.v1.EventType

interface ItemPublisher {
    fun publish(
        eventType: EventType,
        item: Item,
    )
}
