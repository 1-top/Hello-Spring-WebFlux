package com.harrybro.kotlinbasicecommerce

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import java.util.*

@SpringBootTest
class KotlinBasicEcommerceApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun `map과 flatMap test`() {
        val mono: Mono<Int> = Mono.just(1)
        val a: Mono<String> = mono.map { it.toString() }
        val b: Mono<String> = mono.flatMap { Mono.just(it.toString()) }

        // map 의 불편함.
        val o1: Optional<Optional<String>> =
            listOf(Optional.of("a"), Optional.of("b"), Optional.of("c"))
                .stream()
                .filter { it.equals("b") }
                .findAny()
                .map { it.map { v -> v.uppercase() } }

        // flatMap 을 사용하는 이유.
        val o2: Optional<String> =
            listOf(Optional.of("a"), Optional.of("b"), Optional.of("c"))
                .stream()
                .filter { it.equals("b") }
                .findAny()
                .flatMap { it.map { v -> v.uppercase() } }
    }

}
