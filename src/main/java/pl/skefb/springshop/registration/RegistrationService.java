package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.email.EmailSenderService;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.registration.token.ConfirmationToken;
import pl.skefb.springshop.registration.token.ConfirmationTokenService;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserRole;
import pl.skefb.springshop.shopuser.ShopUserService;

import java.time.Instant;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final ShopUserService shopUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    public void register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new ApiRequestException("Nieprawidłowy format emaila " + request.getEmail());
        }
        String token = shopUserService.signUpUser(
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
        emailSenderService.sendEmail(
                request.getEmail(),
                "Weryfikacja konta",
                "Dzień dobry " + request.getFirstName() + "!\n Oto Twój link do weryfikacji konta:\n" +
                        "http://localhost:8080/registration/confirm?token=" + token +
                        "\nŻyczymy udanych zakupów w naszym sklepie!");
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new ApiRequestException("Konto użytkownika zostało już aktywowane");
        }
        confirmationTokenService.setConfirmedAt(token);
        shopUserService.enableShopUser(confirmationToken.getShopUser().getEmail());
    }
}
