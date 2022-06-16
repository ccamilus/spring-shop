package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.email.EmailSenderService;
import pl.skefb.springshop.exception.EmailAlreadyConfirmedException;
import pl.skefb.springshop.exception.EmailNotValidException;
import pl.skefb.springshop.exception.TokenExpiredException;
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

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new EmailNotValidException("Email not valid!");
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
                "Dzień dobry " + request.getFirstName() + "!\n Oto Wwój link do weryfikacji konta:\n" +
                        "http://localhost:8080/registration/confirm?token=" + token + "\n Link wygaśnie " +
                        "za 2 tyognie od otrzymania wiadomości. Życzymy udanych zakupów w naszym sklepie!");
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("Email already confirmed!");
        }
        Instant expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(Instant.now())) {
            throw new TokenExpiredException("Token expired!");
        }
        confirmationTokenService.setConfirmedAt(token);
        shopUserService.enableShopUser(confirmationToken.getShopUser().getEmail());
        return "confirmed";
    }
}
