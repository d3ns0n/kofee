package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest.items

import de.d3ns0n.code.kofee.application.port.incoming.items.CreateItem
import de.d3ns0n.code.kofee.application.port.incoming.items.GetItems
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.application.service.ItemService
import de.d3ns0n.code.kofee.infrastructure.adapter.incoming.rest.items.dto.ItemResponseMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/items")
class ItemController(private val itemService: ItemService, private val itemResponseMapper: ItemResponseMapper) : GetItems, CreateItem {
    @GetMapping
    override fun getItems(): Flux<ItemResponse> {
        return itemService.getItems().map { itemResponseMapper.map(it) }
    }

    @PostMapping
    override fun create(item: CreateItemRequest): Mono<ItemResponse> {
        return itemService.createItem(itemResponseMapper.map(item)).map { itemResponseMapper.map(it) }
    }
}
