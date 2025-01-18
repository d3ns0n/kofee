package de.d3ns0n.code.kofee.application.port.incoming.items.dto

import java.util.UUID

data class ItemResponse(val id: UUID?, val name: String, val price: Double)
