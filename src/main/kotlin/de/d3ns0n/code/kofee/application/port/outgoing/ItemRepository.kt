package de.d3ns0n.code.kofee.application.port.outgoing

import de.d3ns0n.code.kofee.domain.Item
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ItemRepository {
    fun saveItem(item: Item): Mono<Item>

    fun getItem(id: Long): Mono<Item>

    fun getItems(): Flux<Item>
}
