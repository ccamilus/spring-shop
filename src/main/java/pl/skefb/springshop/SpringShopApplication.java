package pl.skefb.springshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringShopApplication {
    @Autowired
    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringShopApplication.class, args);
    }

    //Test EmailSender
    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail() {
        senderService.sendEmail("kobielarz.t@gmail.com",
                "Test subject",
                "Test body");
    }

}
