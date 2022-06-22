package pl.skefb.springshop.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ApiRequestException;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono tokenu " + token));
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, Instant.now());
    }
}
