package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest.items.dto

import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.domain.Item
import org.springframework.stereotype.Component

@Component
class ItemDtoMapper {
    fun mapToItemResponse(item: Item): ItemResponse = ItemResponse(item.id, item.name, item.price)

    fun mapFromCreateItemRequest(createItemRequest: CreateItemRequest): Item =
        Item(
            name = createItemRequest.name,
            price = createItemRequest.price,
        )
}
