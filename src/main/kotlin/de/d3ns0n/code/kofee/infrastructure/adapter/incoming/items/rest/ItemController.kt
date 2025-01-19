package de.d3ns0n.code.kofee.infrastructure.adapter.incoming.items.rest

import de.d3ns0n.code.kofee.application.port.incoming.items.CreateItem
import de.d3ns0n.code.kofee.application.port.incoming.items.GetItems
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.CreateItemRequest
import de.d3ns0n.code.kofee.application.port.incoming.items.dto.ItemResponse
import de.d3ns0n.code.kofee.application.service.ItemService
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService,
    private val itemRequestResponseMapper: ItemRequestResponseMapper,
) : GetItems, CreateItem {
    @GetMapping
    override fun getItems(): Flux<ItemResponse> {
        return itemService.getItems()
            .map { itemRequestResponseMapper.mapToItemResponse(it) }
    }

    @PreAuthorize("hasRole('coffee_farmer')")
    @PostMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    override fun create(
        @RequestBody item: CreateItemRequest,
    ): Mono<ItemResponse> {
        return itemRequestResponseMapper.mapFromCreateItemRequest(item)
            .let { itemService.create(it) }
            .map { itemRequestResponseMapper.mapToItemResponse(it) }
    }
}
