package pl.skefb.springshop.shopuser.shopuseraddress;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShopUserAddressConfig {

    @Bean
    CommandLineRunner commandLineRunnerAddress(ShopUserAddressRepository repository) {
        return args -> {
            ShopUserAddress a1 = new ShopUserAddress(
            );

            repository.saveAll(
                    List.of(a1)
            );
        };
    }
}
