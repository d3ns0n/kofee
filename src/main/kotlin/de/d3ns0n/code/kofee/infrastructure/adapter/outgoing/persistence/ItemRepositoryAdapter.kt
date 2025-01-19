package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.persistence

import de.d3ns0n.code.kofee.application.port.outgoing.ItemRepository
import de.d3ns0n.code.kofee.domain.Item
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ItemRepositoryAdapter(
    private val reactiveDatabaseItemRepository: ReactiveDatabaseItemRepository,
    private val itemMapper: ItemMapper,
) : ItemRepository {
    override fun getItems(): Flux<Item> =
        reactiveDatabaseItemRepository.findAll()
            .map { itemMapper.mapFromEntity(it) }

    override fun saveItem(item: Item): Mono<Item> =
        itemMapper.mapToEntity(item)
            .let { reactiveDatabaseItemRepository.save(it) }
            .map { itemMapper.mapFromEntity(it) }
}
