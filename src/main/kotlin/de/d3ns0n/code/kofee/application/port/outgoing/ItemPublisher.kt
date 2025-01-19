package de.d3ns0n.code.kofee.application.port.outgoing

import de.d3ns0n.code.kofee.domain.Item

interface ItemPublisher {
    fun publish(item: Item)
}
