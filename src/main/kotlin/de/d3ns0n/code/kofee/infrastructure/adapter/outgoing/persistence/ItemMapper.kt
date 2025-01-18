package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.persistence

import de.d3ns0n.code.kofee.domain.Item
import org.springframework.stereotype.Component

@Component
class ItemMapper {
    fun mapFromEntity(itemEntity: ItemEntity): Item = Item(itemEntity.id, itemEntity.name, itemEntity.price)

    fun mapToEntity(item: Item): ItemEntity = ItemEntity(item.id, item.name, item.price)
}
