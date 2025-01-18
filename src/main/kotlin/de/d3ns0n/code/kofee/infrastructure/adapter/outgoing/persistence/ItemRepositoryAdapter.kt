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
    override fun saveItem(item: Item): Mono<Item> {
        return itemMapper.mapToEntity(item).let { reactiveDatabaseItemRepository.save(it) }.map { itemMapper.mapFromEntity(it) }
    }

    override fun getItem(id: Long): Mono<Item> {
        return reactiveDatabaseItemRepository.findById(id).map { itemMapper.mapFromEntity(it) }
    }

    override fun getItems(): Flux<Item> {
        return reactiveDatabaseItemRepository.findAll().map { itemMapper.mapFromEntity(it) }
    }
}
