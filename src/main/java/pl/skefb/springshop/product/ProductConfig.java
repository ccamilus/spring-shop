package pl.skefb.springshop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.skefb.springshop.product.productcategory.ProductCategory;
import pl.skefb.springshop.product.productcategory.ProductCategoryRepository;
import pl.skefb.springshop.product.productinventory.ProductInventory;
import pl.skefb.springshop.product.productinventory.ProductInventoryRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    public CommandLineRunner commandLineRunnerProductConfig(ProductRepository productRepository,
                                                            ProductInventoryRepository productInventoryRepository,
                                                            ProductCategoryRepository productCategoryRepository) {
        return args -> {
            ProductInventory productInventory1 = new ProductInventory(10);
            ProductInventory productInventory2 = new ProductInventory(0);
            ProductInventory productInventory3 = new ProductInventory(8);
            ProductInventory productInventory4 = new ProductInventory(24);
            ProductInventory productInventory5 = new ProductInventory(105);

            ProductCategory productCategory1 =
                    new ProductCategory("Laptopy", "Laptopy/Notebooki/Ultrabooki");

            Product product1 = new Product(
                    "Dell Latitude 3520",
                    "Laptop z obudową bardzo wysokiej jakości, posiadający szereg zabezpieczeń, dzięki" +
                            " którym Twoje dane pozostaną bezpieczne. Jasny ekran 400 nit pozwoli na pracę w każdych" +
                            " warunkach a funkcja ExpressConnect zapewni optymalną łączność z punkami dostępu.",
                    new BigDecimal("3909.16"),
                    "https://images.morele.net/i1064/5948393_0_i1064.jpg",
                    productInventory1,
                    productCategory1
            );
            Product product2 = new Product(
                    "Lenovo IdeaPad 3 15ADA05",
                    "Laptop cechujący się dobrym stosunkiem jakości oraz wydajności do ceny ze względu" +
                            " na starannie dobrane podzespoły. Mała waga oraz długi czas pracy na baterii sprzyjają" +
                            " mobilnej pracy",
                    new BigDecimal("1699.00"),
                    "https://images.morele.net/i1064/5943337_0_i1064.jpg",
                    productInventory2,
                    productCategory1
            );
            Product product3 = new Product(
                    "MSI GF66 Katana 11UG-461XPL",
                    "Dzięki najnowszym procesorom Intel® Core™ i7 11. generacji oraz układom graficznym" +
                            " z serii NVIDIA® GeForce RTX™ 30, komputery z rodziny Katana GF66 został zoptymalizowane" +
                            " do tego, aby uwolnić prawdziwą wydajność podczas rozgrywek. Nowy laptop Katana GF66" +
                            " jest zbudowany z takim samym kunsztem, z jakim wykuwa się ostrza. Pracuj z optymalną" +
                            " wydajnością i zabłyśnij na polu bitwy.",
                    new BigDecimal("6499.00"),
                    "https://images.morele.net/full/9399112_0_f.jpg",
                    productInventory3,
                    productCategory1
            );
            Product product4 = new Product(
                    "HP 255 G8",
                    "Laptop, który dzięki swojej smukłej i lekkiej konstrukcji oraz wydajnym podzespołom" +
                            " idealnie będzie się nadawał do pracy jak i nauki. Układ TPM 2.0 zabezpieczy Twoje" +
                            " dane, a karta WiFi 6 zapewni stabilne połączenie",
                    new BigDecimal("2199.00"),
                    "https://images.morele.net/i1064/9445121_0_i1064.jpg",
                    productInventory4,
                    productCategory1
            );
            Product product5 = new Product(
                    "Dell Alienware x17 R2",
                    "Wynieś swoją rozgrywkę na nowe wyżyny dzięki zaawansowanej technologii Alienware" +
                            " Cryo-tech™, inżynierii elektrycznej i mechanicznej, która utrzymuje stabilność systemu" +
                            " w momentach intensywnej wydajności. Technologia ta obejmuje technologię czterech" +
                            " wentylatorów, unikalne stany zasilania oraz Element 31 — ekskluzywny, galowo-silikonowy" +
                            " materiał interfejsu termicznego firmy Alienware.",
                    new BigDecimal("39405.90"),
                    "https://images.morele.net/i1064/10340173_0_i1064.jpg",
                    productInventory5,
                    productCategory1
            );
            productInventoryRepository.saveAll(List.of(
                    productInventory1,
                    productInventory2,
                    productInventory3,
                    productInventory4,
                    productInventory5
            ));
            productCategoryRepository.save(productCategory1);
            productRepository.saveAll(List.of(
                    product1,
                    product2,
                    product3,
                    product4,
                    product5
            ));
        };
    }
}
