package com.harrybro.hellokotlinwithwebflux.domain.product.dao

import com.harrybro.hellokotlinwithwebflux.domain.product.domain.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

//@Repository
interface ProductRepository: ReactiveMongoRepository<Product, String> {
    fun findAllByPriceBetween(min: Double, max: Double): Flux<Product>
}