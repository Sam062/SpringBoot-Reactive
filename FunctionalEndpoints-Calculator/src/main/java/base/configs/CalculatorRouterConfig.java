package base.configs;

import base.services.CalculatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class CalculatorRouterConfig {
    public static final String INPUT = "{input1}/{input2}";

    @Autowired
    private CalculatorHandler calHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter() {
        return RouterFunctions.route()
                .path("calc", this::serverResponseRouterFunction)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET(INPUT, isOperation("+"), calHandler::additionHandler)
                .GET(INPUT, isOperation("-"), calHandler::subtractionHandler)
                .GET(INPUT, isOperation("*"), calHandler::multiplicationHandler)
                .GET(INPUT, isOperation("/"), calHandler::divisionHandler)
                .GET(INPUT, req -> ServerResponse.badRequest().bodyValue("OP should be + - * /"))
                .build();
    }

    private RequestPredicate isOperation(String op) {
        return RequestPredicates.headers(header -> op.equals(header
                .asHttpHeaders()
                .toSingleValueMap()
                .get("OP")));
    }
}
