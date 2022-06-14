package pl.skefb.springshop;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.skefb.springshop.email.EmailSenderService;

@SpringBootApplication
@AllArgsConstructor
public class SpringShopApplication {
    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringShopApplication.class, args);
    }

    //Test EmailSender
//     @EventListener(ApplicationReadyEvent.class)
//     public void sendEmail() {
//         senderService.sendEmail("kobielarz.t@gmail.com",
//                 "Test subject",
//                 "Test body");
//     }

}
