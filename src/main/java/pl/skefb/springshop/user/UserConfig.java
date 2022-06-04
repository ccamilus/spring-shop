package pl.skefb.springshop.user;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository) {
        return args -> {
            User major = new User(
                    "J00r",
                    "123",
                    "Wojciech",
                    "Suchodolski",
                    123966789,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    "jaj00r@wp.pl"
            );

            User konon = new User(
                    "Konon",
                    "123",
                    "Krzyszftof",
                    "Kononowicz",
                    123456789,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    "konon@wp.pl"
            );

            repository.saveAll(
                    List.of(major, konon)
            );
        };
    }
}
