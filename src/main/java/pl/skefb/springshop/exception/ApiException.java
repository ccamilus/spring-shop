package pl.skefb.springshop.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiException {
    private final String message;
    private final int status;
    private final ZonedDateTime timestamp;
}
