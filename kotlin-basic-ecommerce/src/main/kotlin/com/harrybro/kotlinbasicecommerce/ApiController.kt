package com.harrybro.kotlinbasicecommerce

import org.springframework.web.bind.annotation.*

@RequestMapping("/items")
@RestController
class ItemApiController(private val itemRepository: ItemRepository) {

    @GetMapping
    fun findAllItems() = this.itemRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = this.itemRepository.findById(id)

    @GetMapping("/apiSearch")
    fun findByNameContaining(@RequestParam name: String) = this.itemRepository.findByNameContaining(name)

}