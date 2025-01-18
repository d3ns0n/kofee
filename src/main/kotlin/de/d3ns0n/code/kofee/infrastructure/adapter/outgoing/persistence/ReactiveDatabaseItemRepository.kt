package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.persistence

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ReactiveDatabaseItemRepository : ReactiveCrudRepository<ItemEntity, Long>
