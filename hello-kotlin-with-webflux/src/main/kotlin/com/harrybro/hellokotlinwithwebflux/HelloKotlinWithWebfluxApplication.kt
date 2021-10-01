package com.harrybro.hellokotlinwithwebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloKotlinWithWebfluxApplication

fun main(args: Array<String>) {
    runApplication<HelloKotlinWithWebfluxApplication>(*args)
}
