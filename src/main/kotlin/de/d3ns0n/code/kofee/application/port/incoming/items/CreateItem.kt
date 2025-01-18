package de.d3ns0n.code.kofee.application.port.incoming.items

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import reactor.core.publisher.Mono

interface CreateItem {
    fun create(item: CreateItemRequest): Mono<ItemResponse>
}
