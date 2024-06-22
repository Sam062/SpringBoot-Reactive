package base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class SingleResponseTest extends BaseTestConfig {
    @Autowired
    private WebClient webClient;

    /*
    Trying to hit another service with url:
    http://localhost:9695/squire/{value}
    */
    @Test
    void blockTest() {
        Object resp = this.webClient
                .get()
                .uri("squire/{value}", 12)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        //RESPONSE :: {result=144, date=2024-05-23T15:23:09.662+00:00}
        System.out.println("RESPONSE :: " + resp);

        Assertions.assertNotNull(resp);
        Assertions.assertTrue(resp.toString().contains("144"));

    }

    @Test
    void squireSuccess() {
        Mono<Object> resp = this.webClient
                .get()
                .uri("/squire/{value}", 12)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnNext(System.out::println);

        StepVerifier.create(resp)
                .expectNextMatches(input -> input.toString().contains("144"))
                .verifyComplete();
    }

    @Test
    void squireFailure() {
        Mono<Object> resp = this.webClient
                .get()
                .uri("/squire/{value}", 12)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnNext(System.out::println);

        StepVerifier.create(resp)
                .expectNextMatches(input -> !input.toString().contains("145"))
                .verifyComplete();
    }


    @Test
    void squireBadReqSuccess() {
        Mono<Object> resp = this.webClient
                .get()
                //this url throws Exception if (in < 10 && in > 20)
                .uri("/squire/{value}", 8)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(resp)
                .verifyError(WebClientResponseException.BadRequest.class);
    }

}
