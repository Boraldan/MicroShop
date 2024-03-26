package boraldan.shop.controller.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException e) {
        // обработка ошибки
        // возвращение ResponseEntity с подходящим статусом и сообщением об ошибке
        return new ResponseEntity<>("Feign exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
