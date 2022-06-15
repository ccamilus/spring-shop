package pl.skefb.springshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.email.EmailSenderService;
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
            // TODO: write better exception handler
            throw new IllegalStateException("email not valid");
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
                "Confirmation link",
                "Hello " + request.getFirstName() + ",\n your account verification link:\n" +
                        "http://localhost:8080/registration/confirm?token=" + token +
                        "\n You have 2 weeks to activate your account."
        );
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        Instant expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(Instant.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        shopUserService.enableShopUser(
                confirmationToken.getShopUser().getEmail());
        return "confirmed";
    }
}
