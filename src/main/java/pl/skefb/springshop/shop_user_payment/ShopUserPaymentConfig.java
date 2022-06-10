package pl.skefb.springshop.shop_user_payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class ShopUserPaymentConfig {

    @Bean
    CommandLineRunner commandLineRunnerPayment (ShopUserPaymentRepository shopUserPaymentRepository) {
        ShopUserPayment p1 = new ShopUserPayment(
//                1,
//                "card",
//                "provider",
//                123,
//                null
        );

        return args -> {
            shopUserPaymentRepository.saveAll(
                    List.of(p1)
            );
        };
    }
}
