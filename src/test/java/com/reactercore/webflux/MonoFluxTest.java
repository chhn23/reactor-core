package com.reactercore.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
       Mono<String> name= Mono.just("Deepak").log()
               .then(Mono.error(new RuntimeException("custom error")));
       name.subscribe(System.out::println,e->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> names=Flux.just("Deepak","Amit","Ravi").log()
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("custom error")))
                .concatWithValues("cloud");
        names.subscribe(System.out::println,e->System.out.println(e.getMessage()));
    }
}
