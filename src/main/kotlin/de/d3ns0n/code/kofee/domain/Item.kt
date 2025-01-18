package de.d3ns0n.code.kofee.domain

import java.util.UUID

data class Item(val id: UUID? = null, val name: String, val price: Double)
