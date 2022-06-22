package pl.skefb.springshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            ApiRequestException.class
    })
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
                );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
