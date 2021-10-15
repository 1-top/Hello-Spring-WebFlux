package com.harrybro.kotlinbasicecommerce

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Component

@Component
class DatabaseLoader {

    // CommandLineRunner 는 애플리케이션이 시작된 후 자동으로 실행되는 특수한 스프링 부트 컴포넌트다.
    // run 메서드 하나만 가지고 있는 Functional Interface 이다.
    // reactive programming 에서 블로킹 레포지토리 사용 가능성을 낮추려면 아예 만들지를 말아야한다.
//    @Bean
//    fun initialize(repository: BlockingItemRepository) = CommandLineRunner {
//            repository.save(Item("item1", 1000.0))
//            repository.save(Item("item2", 1000.0))
//        }

    @Bean
    fun initialize(mongo: MongoOperations) = CommandLineRunner {
        mongo.save(Item("item1", 1000.0, "d1"))
        mongo.save(Item("item2", 2000.0, "d2"))
        mongo.save(Item("item3", 3000.0, "d3"))
    }
}