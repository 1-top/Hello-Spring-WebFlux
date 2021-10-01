package com.harrybro.hellokotlinwithwebflux.domain.product.service

import com.harrybro.hellokotlinwithwebflux.domain.product.dao.ProductRepository
import com.harrybro.hellokotlinwithwebflux.domain.product.dto.ProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun save(dto: ProductDto.SaveRequest): Mono<ProductDto.Response>
    fun findAll(): Flux<ProductDto.Response>
    fun findById(id: String): Mono<ProductDto.Response>
    fun findAllByPriceBetween(min: Double? = 0.0, max: Double? = Double.MAX_VALUE): Flux<ProductDto.Response>

    @Transactional
    fun update(id: String, dto: ProductDto.UpdateRequest): Mono<ProductDto.Response>
    fun delete(id: String): Mono<Void>
}

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun save(dto: ProductDto.SaveRequest) =
        this.productRepository.save(dto.toEntity()).map { ProductDto.Response(it) }

    override fun findAll() = this.productRepository.findAll().map { ProductDto.Response(it) }

    override fun findById(id: String) = this.productRepository.findById(id).map { ProductDto.Response(it) }

    override fun findAllByPriceBetween(min: Double?, max: Double?) =
        this.productRepository.findAllByPriceBetween(min, max).map { ProductDto.Response(it) }

    override fun update(id: String, dto: ProductDto.UpdateRequest) = this.productRepository.findById(id)
        .doOnNext { it.update(dto) }
        .flatMap { this.productRepository.save(it) }
        .map { ProductDto.Response(it) }

    override fun delete(id: String) = this.productRepository.deleteById(id)
}