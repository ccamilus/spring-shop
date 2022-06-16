package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.UserNotFoundException;
import pl.skefb.springshop.registration.token.ConfirmationToken;
import pl.skefb.springshop.registration.token.ConfirmationTokenService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShopUserService implements UserDetailsService {
    private final ShopUserRepository shopUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return shopUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    public String signUpUser(ShopUser shopUser) {
        boolean userExists = shopUserRepository.existsByEmail(shopUser.getEmail());
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(shopUser.getPassword());
        shopUser.setPassword(encodedPassword);
        shopUserRepository.save(shopUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                Instant.now(),
                Instant.now().plus(14, ChronoUnit.DAYS), shopUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableShopUser(String email) {
        shopUserRepository.enableAppUser(email);
    }

    public List<ShopUser> getUsers() {
        return shopUserRepository.findAll();
    }

    public ShopUser getUserById(Long id) {
        return shopUserRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User " + id + " not found!"));
    }

    public void deleteUser(Long id) {
        boolean exists = shopUserRepository.existsById(id);
        if (!exists) {
            throw new UserNotFoundException("User with id " + id + " does not exist!");
        }
        shopUserRepository.deleteById(id);
    }

    public ShopUser findByEmail(String email) {
        return shopUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }
}
