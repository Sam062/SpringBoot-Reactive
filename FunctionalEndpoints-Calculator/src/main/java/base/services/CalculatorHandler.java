package base.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {

    public Mono<ServerResponse> additionHandler(ServerRequest req) {
        return process(req, (input1, input2) -> (ServerResponse.ok().bodyValue(input1 + input2)));
    }

    public Mono<ServerResponse> subtractionHandler(ServerRequest req) {
        return process(req, (input1, input2) -> (ServerResponse.ok().bodyValue(input1 - input2)));
    }

    public Mono<ServerResponse> multiplicationHandler(ServerRequest req) {
        return process(req, (input1, input2) -> (ServerResponse.ok().bodyValue(input1 * input2)));
    }

    public Mono<ServerResponse> divisionHandler(ServerRequest req) {
        return process(req, (input1, input2) -> input2 != 0 ? (ServerResponse.ok().bodyValue(input1 / input2)) : (ServerResponse.badRequest().bodyValue("input2 can't be 0")));
    }


    private Mono<ServerResponse> process(ServerRequest req, BiFunction<Integer, Integer, Mono<ServerResponse>> opLogic) {
        Integer input1 = getValue(req, "input1");
        Integer input2 = getValue(req, "input2");

        return opLogic.apply(input1, input2);
    }

    private Integer getValue(ServerRequest req, String key) {
        return Integer.valueOf(req.pathVariable(key));
    }
}
