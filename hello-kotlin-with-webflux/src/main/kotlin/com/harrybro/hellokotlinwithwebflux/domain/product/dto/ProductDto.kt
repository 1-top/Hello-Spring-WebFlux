package com.harrybro.hellokotlinwithwebflux.domain.product.dto

import com.harrybro.hellokotlinwithwebflux.domain.product.domain.Product

class ProductDto {
    data class SaveRequest(
        val name: String,
        val quantity: Int,
        val price: Double,
    ) {
        fun toEntity() = Product(name, quantity, price)
    }

    data class Response(
        val id: String,
        val name: String,
        val quantity: Int,
        val price: Double,
    ) {
        constructor(product: Product): this(product.id?: "null", product.name, product.quantity, product.price)
    }

    data class UpdateRequest(val name: String?, val price: Double?)

}