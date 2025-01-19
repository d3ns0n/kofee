package de.d3ns0n.code.kofee.application.service

import de.d3ns0n.code.kofee.application.port.outgoing.ItemPublisher
import de.d3ns0n.code.kofee.application.port.outgoing.ItemRepository
import de.d3ns0n.code.kofee.domain.Item
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val itemPublisher: ItemPublisher,
) {
    fun getItems(): Flux<Item> {
        return itemRepository.getItems()
    }

    @Transactional("connectionFactoryTransactionManager")
    fun create(item: Item): Mono<Item> {
        return itemRepository.saveItem(item)
            .doOnNext { itemPublisher.publish(it) }
    }
}
