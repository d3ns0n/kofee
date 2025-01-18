package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest.items.dto

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.domain.Item
import org.springframework.stereotype.Component

@Component
class ItemResponseMapper {
    fun map(item: Item): ItemResponse = ItemResponse(item.id, item.name, item.price)

    fun map(createItemRequest: CreateItemRequest): Item =
        Item(
            name = createItemRequest.name,
            price = createItemRequest.price,
        )
}
