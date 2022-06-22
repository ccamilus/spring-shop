package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.response.ResponseHandler;

import java.net.URI;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) {
        if (request.getFirstName() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano imienia", HttpStatus.BAD_REQUEST);
        }
        if (request.getLastName() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano nazwiska", HttpStatus.BAD_REQUEST);
        }
        if (request.getEmail() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano emaila", HttpStatus.BAD_REQUEST);
        }
        if (request.getPassword() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano hasła", HttpStatus.BAD_REQUEST);
        }
        if (request.getTelephone() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano numeru telefonu", HttpStatus.BAD_REQUEST);
        }
        registrationService.register(request);
        return ResponseHandler
                .generateResponseWithoutData("Pomyślnie zarejestrowano użytkownika", HttpStatus.CREATED);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<Void> confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:8081")).build();
    }
}
