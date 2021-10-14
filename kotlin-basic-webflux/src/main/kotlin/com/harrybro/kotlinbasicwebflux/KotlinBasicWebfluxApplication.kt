package com.harrybro.kotlinbasicwebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*

@SpringBootApplication
class KotlinBasicWebfluxApplication

fun main(args: Array<String>) {
    runApplication<KotlinBasicWebfluxApplication>(*args)
}

@Controller
class HomeController {
    @GetMapping
    fun home() = Mono.just("home")
}

@RestController
class ServerController(private val kitchenService: KitchenService) {
    @GetMapping("/server", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun serveDishes() = this.kitchenService.getDishes()

    @GetMapping("/served-dishes", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun deliverDishes() = this.kitchenService.getDishes().map(Dish::deliver)
}

@Service
class KitchenService {
    private val menu = mutableListOf(Dish("짜장면"), Dish("짬뽕"), Dish("탕수육"))
    private val random = Random()

    fun getDishes() = Flux.generate<Dish> { sink -> sink.next(randomDish()) }
        .delayElements(Duration.ofMillis(250))

    private fun randomDish() = this.menu[random.nextInt(this.menu.size)]
}

class Dish(var description: String, var delivered: Boolean = false) {
    override fun toString(): String = "Dish(description='$description', delivered=$delivered)"
}

fun Dish.deliver() = Dish(this.description).apply { delivered = true }