package de.d3ns0n.code.kofee.infrastructure.adapter.outgoing.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("item")
data class ItemEntity(
    @Id val id: UUID?,
    val name: String,
    val price: Double,
)
