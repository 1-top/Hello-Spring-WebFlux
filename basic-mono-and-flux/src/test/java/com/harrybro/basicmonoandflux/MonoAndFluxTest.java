package com.harrybro.basicmonoandflux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoAndFluxTest {

    @Test
    @DisplayName("mono test")
    void monoTest() {
//        Mono<String> monoStr = Mono.just("HarryBro");
        Mono<?> monoStr = Mono.just("HarryBro")
                .then(Mono.error(new RuntimeException("Exception occurred."))) // 강제로 에러 발생시킴.
                .log(); // 실행 순서를 로그로 찍어준다.
//        monoStr.subscribe(System.out::println);
        // 2번째 매개변수로 발생한 에러를 받을 수 있다.
        monoStr.subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    @DisplayName("flux test")
    void fluxTest() {
        Flux<String> fluxStr = Flux.just("Hello", "Flux", "Good")
                .concatWithValues("add1", "add2") // 추가로 값을 Flux에 넣는다.
                .log();
        fluxStr.subscribe(System.out::println);
    }

}
