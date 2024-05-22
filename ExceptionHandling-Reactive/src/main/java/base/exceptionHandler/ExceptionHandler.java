package base.exceptionHandler;

import base.models.InvalidInputResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<InvalidInputResponse> invalidInputHandler
            (InvalidInputException ex) {
        return ResponseEntity
                .badRequest()
                .body(new InvalidInputResponse(ex.getInput(), ex.getErrorMsg(), new Date()));
    }

}
