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
    CommandLineRunner CLRproduct(
            ProductRepository product,
            ProductCategoryRepository category,
            ProductInventoryRepository inventory
    ) {
        return args -> {
            ProductCategory CATaccessories = new ProductCategory(
                    "accessories",
                    "electronical accesories"
            );
            ProductCategory CATLaptops = new ProductCategory(
                    "laptops",
                    "laptops"
            );
            ProductCategory CATMobile = new ProductCategory(
                    "mobile",
                    "mobile phones"
            );

            ProductInventory INVsampleA = new ProductInventory(12);
            ProductInventory INVsampleB = new ProductInventory(75);
            ProductInventory INVsampleC = new ProductInventory(3);
            ProductInventory INVsampleD = new ProductInventory(45);
            ProductInventory INVsampleE = new ProductInventory(0);
            ProductInventory INVsampleF = new ProductInventory(13);

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

            Product sampleC = new Product(
                    "Macbook air m1",
                    "mac laptop m1 16gb itp itp",
                    "6666",
                    5300.99,
                    CATLaptops,
                    INVsampleC
            );

            Product sampleD = new Product(
                    "LG",
                    "this mobile phone sucks",
                    "5555",
                    2.09,
                    CATMobile,
                    INVsampleD
            );

            Product sampleE = new Product(
                    "Samsung Galaxy J5000",
                    "specification",
                    "4444",
                    1300.99,
                    CATMobile,
                    INVsampleE
            );

            Product sampleF = new Product(
                    "Xiaomi Redmi 10",
                    "That's mine",
                    "3333",
                    1000,
                    CATMobile,
                    INVsampleF
            );

            category.saveAll(List.of(
                    CATaccessories, CATLaptops, CATMobile
            ));
            inventory.saveAll(List.of(
                    INVsampleA, INVsampleB, INVsampleC, INVsampleD, INVsampleE, INVsampleF
            ));
            product.saveAll(List.of(
                    sampleA, sampleB, sampleC, sampleD, sampleE, sampleF
            ));
        };
    }
}
