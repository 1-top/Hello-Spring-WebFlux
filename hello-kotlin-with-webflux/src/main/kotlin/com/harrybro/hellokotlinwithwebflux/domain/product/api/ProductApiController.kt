package com.harrybro.hellokotlinwithwebflux.domain.product.api

import com.harrybro.hellokotlinwithwebflux.domain.product.dto.ProductDto
import com.harrybro.hellokotlinwithwebflux.domain.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductApiController(private val productService: ProductService) {

    @PostMapping
    fun save(@RequestBody request: ProductDto.SaveRequest) = this.productService.save(request)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = this.productService.findById(id)

    @GetMapping
    fun findByAll() = this.productService.findAll()

    @GetMapping("/price")
    fun findByPriceBetween(@RequestParam min: Double, @RequestParam max: Double) =
        this.productService.findAllByPriceBetween(min, max)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody request: ProductDto.UpdateRequest) =
        this.productService.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = this.productService.delete(id)

}