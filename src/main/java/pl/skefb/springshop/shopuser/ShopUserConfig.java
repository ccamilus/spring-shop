package pl.skefb.springshop.shopuser;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.List;

import static pl.skefb.springshop.shopuser.ShopUserRole.ADMIN;
import static pl.skefb.springshop.shopuser.ShopUserRole.USER;

@Configuration
@AllArgsConstructor
public class ShopUserConfig {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CommandLineRunner loadData(ShopUserRepository shopUserRepository) {
        return args -> {
            ShopUser jan = new ShopUser(
                    "Jan",
                    "Kowalski",
                    "jan@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    789456123,
                    Instant.now(),
                    ADMIN
            );
            jan.setEnabled(true);
            ShopUser adam = new ShopUser(
                    "Adam",
                    "Nowak",
                    "adam@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    987654321,
                    Instant.now(),
                    USER
            );
            adam.setEnabled(true);
            ShopUser marek = new ShopUser(
                    "Marek",
                    "Kowalik",
                    "marek@wp.pl",
                    bCryptPasswordEncoder.encode("pass"),
                    420692137,
                    Instant.now(),
                    USER
            );
            marek.setEnabled(true);
            shopUserRepository.saveAll(
                    List.of(
                            jan,
                            adam,
                            marek
                    )
            );
        };
    }
}
