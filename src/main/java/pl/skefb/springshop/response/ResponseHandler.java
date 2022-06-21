package pl.skefb.springshop.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", responseObj);
        map.put("message", message);
        map.put("status", status.value());
        map.put("timestamp", ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponseWithoutData(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("timestamp", ZonedDateTime.now(ZoneId.of("Europe/Warsaw")));
        return new ResponseEntity<Object>(map, status);
    }
}
