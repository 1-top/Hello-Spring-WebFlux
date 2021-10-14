package com.harrybro.basicmonoandflux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MonoAndFluxTest {

    private static Logger log = LoggerFactory.getLogger(MonoAndFluxTest.class);

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
                .concatWith(Flux.error(new RuntimeException("Exception occurred.")))
                .concatWithValues("add3") // 중간에 에러가 발생하면 그 다음 onNext()는 실행되지 않는다.
                .log();
        fluxStr.subscribe(System.out::println);
    }

    @Test
    @DisplayName("flux range test")
    void fluxRangeTest() {
        Flux<Integer> flux = Flux.range(1, 10)
                .subscribeOn(Schedulers.newSingle("sub")) // 새로운 스레드풀 만듦
                .log();
        flux.subscribe(System.out::println);
    }

    @Test
    @DisplayName("flux interval test")
    void fluxIntervalTest() throws InterruptedException {
        Flux.interval(Duration.ofMillis(500))
                .take(5) // onNext를 몇 개 까지 사용 가능하게할지 설정
                .subscribe(i -> log.info(String.valueOf(i)));
        TimeUnit.SECONDS.sleep(5); // main thread가 먼저 종료되는 것을 방지
        log.info("end");
    }
}
