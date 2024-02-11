package base.controllers;

import base.model.DemoUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
public class DemoController {

    @GetMapping("/")
    public String test() {
        System.out.println("Up and running...");
        return "Up and running...";
    }


    @GetMapping("/getEvent")
    public ResponseEntity<Mono<DemoUser>> getEvent() {
        System.out.println("Getting event...");
        Mono<DemoUser> mono = Mono.just(new DemoUser(101, "SAM"));
        return new ResponseEntity<>(mono, HttpStatus.OK);
    }

    @GetMapping(value = "/getEvents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<DemoUser>> getEvents() {
        System.out.println("Getting events...");

        DemoUser user1 = new DemoUser(101, "Sam");
        DemoUser user2 = new DemoUser(102, "Shivam");

        Stream<DemoUser> userStream = Stream.of(user1, user2);

        Flux<DemoUser> flux = Flux.fromStream(userStream);
        Flux<Long> interval = flux.interval(Duration.ofSeconds(5));

        Flux<Tuple2<Long, DemoUser>> fluxZip = Flux.zip(interval, flux);

        Flux<DemoUser> map = fluxZip.map(Tuple2::getT2);


        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
