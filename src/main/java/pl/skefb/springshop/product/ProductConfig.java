package pl.skefb.springshop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    public CommandLineRunner commandLineRunnerProductConfig(ProductRepository productRepository) {
        return args -> {
            productRepository.saveAll(List.of(
                    new Product(
                            "Dell Latitude 3520",
                            "Laptop z obudową bardzo wysokiej jakości, posiadający szereg zabezpieczeń, dzięki którym Twoje dane pozostaną bezpieczne. Jasny ekran 400 nit pozwoli na pracę w każdych warunkach a funkcja ExpressConnect zapewni optymalną łączność z punkami dostępu.",
                            3909.16,
                            "https://images.morele.net/i1064/5948393_0_i1064.jpg"
                    ),
                    new Product(
                            "Lenovo IdeaPad 3 15ADA05",
                            "Laptop cechujący się dobrym stosunkiem jakości oraz wydajności do ceny ze względu na starannie dobrane podzespoły. Mała waga oraz długi czas pracy na baterii sprzyjają mobilnej pracy",
                            1699,
                            "https://images.morele.net/i1064/5943337_0_i1064.jpg"
                    ),
                    new Product(
                            "MSI GF66 Katana 11UG-461XPL",
                            "Dzięki najnowszym procesorom Intel® Core™ i7 11. generacji oraz układom graficznym z serii NVIDIA® GeForce RTX™ 30, komputery z rodziny Katana GF66 został zoptymalizowane do tego, aby uwolnić prawdziwą wydajność podczas rozgrywek. Nowy laptop Katana GF66 jest zbudowany z takim samym kunsztem, z jakim wykuwa się ostrza. Pracuj z optymalną wydajnością i zabłyśnij na polu bitwy.",
                            6499,
                            "https://images.morele.net/full/9399112_0_f.jpg"
                    ),
                    new Product(
                            "HP 255 G8",
                            "Laptop, który dzięki swojej smukłej i lekkiej konstrukcji oraz wydajnym podzespołom idealnie będzie się nadawał do pracy jak i nauki. Układ TPM 2.0 zabezpieczy Twoje dane, a karta WiFi 6 zapewni stabilne połączenie",
                            2199,
                            "https://images.morele.net/i1064/9445121_0_i1064.jpg"
                    ),
                    new Product(
                            "Dell Alienware x17 R2",
                            "Wynieś swoją rozgrywkę na nowe wyżyny dzięki zaawansowanej technologii Alienware Cryo-tech™, inżynierii elektrycznej i mechanicznej, która utrzymuje stabilność systemu w momentach intensywnej wydajności. Technologia ta obejmuje technologię czterech wentylatorów, unikalne stany zasilania oraz Element 31 — ekskluzywny, galowo-silikonowy materiał interfejsu termicznego firmy Alienware.",
                            39405.90,
                            "https://images.morele.net/i1064/10340173_0_i1064.jpg"
                    )
            ));
        };
    }
}
