package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.registration.token.ConfirmationToken;
import pl.skefb.springshop.registration.token.ConfirmationTokenService;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressRequest;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentRequest;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShopUserService implements UserDetailsService {
    private final ShopUserRepository shopUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserPaymentService shopUserPaymentService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return shopUserRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono użytkownika o emailu " + email));
    }

    public String signUpUser(ShopUser shopUser) {
        boolean userExists = shopUserRepository.existsByEmail(shopUser.getEmail());
        if (userExists) {
            throw new ApiRequestException("Użytkownik z mailem " + shopUser.getEmail() + " już istnieje");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(shopUser.getPassword());
        shopUser.setPassword(encodedPassword);
        shopUserRepository.save(shopUser);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                Instant.now(),
                shopUser
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

    public ShopUser getShopUserInformation(Authentication authentication) {
        return findByEmail(authentication.getName());
    }

    public ShopUser findByEmail(String email) {
        return shopUserRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono użytkownika o emailu " + email));
    }

    public void saveNewShopUserAddress(ShopUserAddressRequest shopUserAddressRequest, Authentication authentication) {
        ShopUser shopUser = findByEmail(authentication.getName());
        shopUserAddressService.saveNewShopUserAddress(
                new ShopUserAddress(
                        shopUser,
                        shopUserAddressRequest.getAddressLine(),
                        shopUserAddressRequest.getTelephone(),
                        shopUserAddressRequest.getCity(),
                        shopUserAddressRequest.getPostalCode(),
                        shopUserAddressRequest.getCountry()
                )
        );
    }

    public void saveNewShopUserPayment(ShopUserPaymentRequest shopUserPaymentRequest, Authentication authentication) {
        ShopUser shopUser = findByEmail(authentication.getName());
        shopUserPaymentService.saveNewShopUserPayment(
                new ShopUserPayment(
                        shopUser,
                        shopUserPaymentRequest.getCardNumber(),
                        shopUserPaymentRequest.getExpirationDate(),
                        shopUserPaymentRequest.getCvvCode()
                )
        );
    }
}
