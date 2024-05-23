package base.controllers;

import base.exceptionHandler.InvalidInputException;
import base.models.MathResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
public class MathController {

    @GetMapping("/squire/{value}")
    public Mono<MathResponse> getSquire(@PathVariable Integer value) {
        return Mono.just(value)
                .handle((input, sink) -> {
                    if (input < 10 || input > 20)
                        sink.error(new InvalidInputException(input));
                    else
                        sink.next(input);
                })
                .cast(Integer.class)
                .map(in -> new MathResponse(in * in, new Date()));
    }

}
