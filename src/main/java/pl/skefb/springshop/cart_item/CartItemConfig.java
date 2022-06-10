package pl.skefb.springshop.cart_item;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.List;

@Configuration
public class CartItemConfig {

    @Bean
    CommandLineRunner commandLineRunnerCartItem(CartItemRepository repository) {
        return args -> {
            CartItem c1 = new CartItem(
                    1,
                    15,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
            repository.saveAll(
                    List.of(c1)
            );
        };
    }
}
