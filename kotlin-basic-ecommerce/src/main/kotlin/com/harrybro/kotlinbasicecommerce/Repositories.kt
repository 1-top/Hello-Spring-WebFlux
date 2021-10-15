package com.harrybro.kotlinbasicecommerce

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ItemRepository : ReactiveCrudRepository<Item, String> {
    fun findByNameContaining(name: String): Flux<Item>
}

//interface BlockingItemRepository : CrudRepository<Item, String> // Blocking 은 아예 만들지 않는 것이 좋다.

interface ItemByExampleRepository : ItemRepository, ReactiveQueryByExampleExecutor<Item>

interface CartRepository : ReactiveCrudRepository<Cart, String>