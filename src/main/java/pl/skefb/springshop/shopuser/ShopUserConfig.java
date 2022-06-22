package pl.skefb.springshop.shopuser;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionRepository;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressRepository;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.List;

import static pl.skefb.springshop.shopuser.ShopUserRole.ADMIN;
import static pl.skefb.springshop.shopuser.ShopUserRole.USER;

@Configuration
@AllArgsConstructor
public class ShopUserConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CommandLineRunner commandLineRunnerShopUserConfig(ShopUserRepository shopUserRepository,
                                                             ShopUserAddressRepository shopUserAddressRepository,
                                                             ShopUserPaymentRepository shopUserPaymentRepository,
                                                             ShoppingSessionRepository shoppingSessionRepository) {
        return args -> {
            ShopUser jan = new ShopUser(
                    "Jan",
                    "Kowalski",
                    "jan@springshop.skefb.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    "789456123",
                    Instant.now(),
                    USER
            );
            jan.setEnabled(true);
            ShopUser adam = new ShopUser(
                    "Adam",
                    "Nowak",
                    "adam@springshop.skefb.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    "987654321",
                    Instant.now(),
                    ADMIN
            );
            adam.setEnabled(true);
            ShopUserAddress janAddress1 = new ShopUserAddress(
                    jan,
                    "Szkolna 17",
                    "789456123",
                    "Białystok",
                    "12-345",
                    "Polska"
            );
            ShopUserPayment janPayment1 = new ShopUserPayment(
                    jan,
                    "1234123412341234",
                    YearMonth.now().plusMonths(5),
                    "123"
            );

            ShoppingSession shoppingSession = new ShoppingSession(
                    jan,
                    new BigDecimal("0.00"),
                    Instant.now()
            );

            shopUserRepository.saveAll(
                    List.of(
                            jan,
                            adam
                    )
            );
            shopUserAddressRepository.save(janAddress1);
            shopUserPaymentRepository.save(janPayment1);
            shoppingSessionRepository.save(shoppingSession);
        };
    }
}
