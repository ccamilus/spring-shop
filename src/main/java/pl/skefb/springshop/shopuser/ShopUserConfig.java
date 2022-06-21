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
                    "jan@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    "789456123",
                    Instant.now(),
                    ADMIN
            );
            jan.setEnabled(true);
            ShopUser adam = new ShopUser(
                    "Adam",
                    "Nowak",
                    "adam@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    "987654321",
                    Instant.now(),
                    USER
            );
            adam.setEnabled(true);
            ShopUser marek = new ShopUser(
                    "Marek",
                    "Kowalik",
                    "marek@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    "420692137",
                    Instant.now(),
                    USER
            );
            marek.setEnabled(true);
            ShopUserAddress janAddress1 = new ShopUserAddress(
                    jan,
                    "Szkolna 17",
                    "789456123",
                    "Starosielce",
                    "66-666",
                    "Bombas"
            );
            ShopUserAddress janAddress2 = new ShopUserAddress(
                    jan,
                    "Rzemieślnicza 23",
                    "123456789",
                    "Białystok",
                    "77-888",
                    "Polska"
            );
            ShopUserPayment janPayment1 = new ShopUserPayment(
                    jan,
                    "1234123412341234",
                    YearMonth.now().plusMonths(5),
                    "666"
            );
            ShopUserPayment janPayment2 = new ShopUserPayment(
                    jan,
                    "7894789478947894",
                    YearMonth.now().plusYears(2),
                    "789"
            );

            ShoppingSession shoppingSession = new ShoppingSession(
                    jan,
                    0,
                    Instant.now()
            );

            shopUserRepository.saveAll(
                    List.of(
                            jan,
                            adam,
                            marek
                    )
            );
            shopUserAddressRepository.saveAll(
                    List.of(
                            janAddress1,
                            janAddress2
                    )
            );
            shopUserPaymentRepository.saveAll(
                    List.of(
                            janPayment1,
                            janPayment2
                    )
            );
            shoppingSessionRepository.save(shoppingSession);
        };
    }
}
