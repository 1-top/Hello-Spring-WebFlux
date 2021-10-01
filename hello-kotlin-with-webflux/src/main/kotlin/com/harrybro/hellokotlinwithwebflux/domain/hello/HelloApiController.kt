package com.harrybro.hellokotlinwithwebflux.domain.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloApiController(private val helloService: HelloService) {

    @GetMapping("/hello")
    fun hello() = this.helloService.hello()
}