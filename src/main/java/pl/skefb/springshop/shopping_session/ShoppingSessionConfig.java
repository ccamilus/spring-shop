package pl.skefb.springshop.shopping_session;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Configuration
public class ShoppingSessionConfig {

    @Bean
    CommandLineRunner commandLineRunnerShoppingSession(ShoppingSessionRepository repository) {
        return args -> {
            ShoppingSession s1 = new ShoppingSession(
                    1,
                    new BigDecimal(10),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );

            repository.saveAll(
                    List.of(s1)
            );
        };
    }
}
