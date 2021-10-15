package com.harrybro.kotlinbasicecommerce

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Mono

@Controller
class HomeController(private val itemRepository: ItemRepository, private val cartRepository: CartRepository) {

    @GetMapping
    fun home() = Mono.just(Rendering.view("home")
        .modelAttribute("items", this.itemRepository.findAll())
        .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(Cart(id = "My Cart")))
        .build())
}

@RequestMapping("/cart")
@Controller
class CartController(private val cartService: CartService) {

    @PostMapping("/{itemId}")
    fun addToCart(@PathVariable itemId: String) = this.cartService.addToCart("My Cart", itemId)
}

@RequestMapping("/item")
@RestController
class ItemController(private val itemService: ItemService) {

    @GetMapping("search")
    fun searchItem(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) description: String?,
        @RequestParam(defaultValue = "true") useAnd: Boolean,
    ) = this.itemService.search(name, description, useAnd)
}