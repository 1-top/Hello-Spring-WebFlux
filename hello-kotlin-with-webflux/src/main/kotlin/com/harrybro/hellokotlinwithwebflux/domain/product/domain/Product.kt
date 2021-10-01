package com.harrybro.hellokotlinwithwebflux.domain.product.domain

import com.harrybro.hellokotlinwithwebflux.domain.product.dto.ProductDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Product(
    var name: String,
    var quantity: Int,
    var price: Double,
    @Id var id: String? = null,
) {
    fun update(dto: ProductDto.UpdateRequest) = this.apply {
        name = dto.name?: this.name
        price = dto.price?: this.price
    }
}