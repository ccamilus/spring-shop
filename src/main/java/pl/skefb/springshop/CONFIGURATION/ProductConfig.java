package pl.skefb.springshop.CONFIGURATION;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductRepository;
import pl.skefb.springshop.product_category.ProductCategory;
import pl.skefb.springshop.product_category.ProductCategoryRepository;
import pl.skefb.springshop.product_inventory.ProductInventory;
import pl.skefb.springshop.product_inventory.ProductInventoryRepository;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            ProductRepository product,
            ProductCategoryRepository category,
            ProductInventoryRepository inventory
    ) {
        return args -> {
            ProductCategory CATaccessories = new ProductCategory(
                    "accessories",
                    "electronical accesories"
            );

            ProductInventory INVsampleA = new ProductInventory(12);
            ProductInventory INVsampleB = new ProductInventory(75);

            Product sampleA = new Product(
                    "JBL 450gl",
                    "headphones",
                    "8888",
                    157.95,
                    CATaccessories,
                    INVsampleA
            );

            Product sampleB = new Product(
                    "China Mobile",
                    "computer keyboard",
                    "7777",
                    75.00,
                    CATaccessories,
                    INVsampleB
            );

            category.saveAll(List.of(
                    CATaccessories
            ));
            inventory.saveAll(List.of(
                    INVsampleA, INVsampleB
            ));
            product.saveAll(List.of(
                    sampleA, sampleB
            ));
        };
    }
}
