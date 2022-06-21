package pl.skefb.springshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.NOT_FOUND.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
                );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.NOT_FOUND.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailNotValidException.class)
    public ResponseEntity<Object> exception(EmailNotValidException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<Object> exception(TokenExpiredException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyConfirmedException.class)
    public ResponseEntity<Object> exception(EmailAlreadyConfirmedException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyTakenException.class)
    public ResponseEntity<Object> exception(EmailAlreadyTakenException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotExistException.class)
    public ResponseEntity<Object> exception(UserNotExistException exception) {
        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
