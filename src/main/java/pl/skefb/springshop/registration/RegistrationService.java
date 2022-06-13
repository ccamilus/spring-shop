package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserRole;
import pl.skefb.springshop.shopuser.ShopUserService;

import java.time.Instant;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final ShopUserService shopUserService;

    public void register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            // TODO: write better exception handler
            throw new IllegalStateException("email not valid");
        }
        shopUserService.signUpUser(
                new ShopUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTelephone(),
                        Instant.now(),
                        ShopUserRole.USER
                )
        );
    }
}
