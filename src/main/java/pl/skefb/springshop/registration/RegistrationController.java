package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.response.ResponseHandler;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
        return ResponseHandler
                .generateResponseWithoutData("Pomyślnie zarejestrowano użytkownika", HttpStatus.CREATED);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<Object> confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return ResponseHandler
                .generateResponseWithoutData("Pomyślnie aktywowano konto", HttpStatus.OK);
    }
}
