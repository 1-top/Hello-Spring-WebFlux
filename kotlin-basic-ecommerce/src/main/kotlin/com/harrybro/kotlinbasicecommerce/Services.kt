package com.harrybro.kotlinbasicecommerce

import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CartService(private val itemRepository: ItemRepository, private val cartRepository: CartRepository) {

    fun addToCart(cartId: String, itemId: String) = this.cartRepository.findById(cartId)
        .defaultIfEmpty(Cart(id = cartId))
        .flatMap { cart ->
            cart.cartItems.stream()
                .filter { cartItem -> cartItem.item.id.equals(itemId) }
                .findAny()
                .map { cartItem ->
                    cartItem.quantity++
                    Mono.just(cart)
                }.orElseGet {
                    this.itemRepository.findById(itemId)
                        .map {
                            cart.cartItems.add(CartItem(it))
                            cart
                        }
                }
        }.flatMap(this.cartRepository::save)
        .thenReturn("redirect:/")
}

@Service
class ItemService(private val itemByExampleRepository: ItemByExampleRepository) {

    fun search(name: String?, description: String?, useAnd: Boolean) =
        (if (useAnd) ExampleMatcher.matchingAll() else ExampleMatcher.matchingAny())
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            .withIgnoreCase()
            .withIgnorePaths("price")
            .let {
                this.itemByExampleRepository.findAll(Example.of(Item(name ?: "", 0.0, description ?: ""), it))
            }
}