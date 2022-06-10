package pl.skefb.springshop.CONFIGURATION;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.skefb.springshop.order_details.OrderDetails;
import pl.skefb.springshop.order_details.OrderDetailsRepository;
import pl.skefb.springshop.order_item.OrderItems;
import pl.skefb.springshop.order_item.OrderItemsRepository;
import pl.skefb.springshop.payment_details.PaymentDetails;
import pl.skefb.springshop.payment_details.PaymentDetailsRepository;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductRepository;
import pl.skefb.springshop.product_category.ProductCategory;
import pl.skefb.springshop.product_category.ProductCategoryRepository;
import pl.skefb.springshop.product_inventory.ProductInventory;
import pl.skefb.springshop.product_inventory.ProductInventoryRepository;

import java.util.List;

@Configuration
public class ProductOrderConfig {

    @Bean
    CommandLineRunner CLRproductOrder(
            ProductRepository product,
            ProductCategoryRepository category,
            ProductInventoryRepository inventory,
            OrderDetailsRepository order,
            OrderItemsRepository orderItems,
            PaymentDetailsRepository payment
    ) {
        ProductCategory CATagd = new ProductCategory(
                "AGD",
                "AGD devices"
        );
        ProductCategory CATphoto = new ProductCategory(
                "cameras",
                "Photo devices"
        );

        ProductInventory INVsampleA = new ProductInventory();
        ProductInventory INVsampleB = new ProductInventory();

        Product sampleA = new Product(
                "whirlpool g12",
                "washing machine",
                "2222",
                1600.00,
                CATagd,
                INVsampleA
        );
        INVsampleA.setQuantity(5);

        Product sampleB = new Product(
                "Xaomi Miphoto 5",
                "Photo camera",
                "1111",
                574.54,
                CATphoto,
                INVsampleB
        );
        INVsampleB.setQuantity(15);

        PaymentDetails payA = new PaymentDetails(
                1,
                "Company sa",
                "in progress"
        );
        PaymentDetails payB = new PaymentDetails(
                1,
                "Industry",
                "accepted"
        );

        OrderDetails orderA = new OrderDetails(
                14000,
                payA
        );
        OrderDetails orderB = new OrderDetails(
                3600,
                payB
        );

        OrderItems odetailAa = new OrderItems(
                2,
                sampleB,
                orderA
        );
        OrderItems odetailAb = new OrderItems(
                4,
                sampleA,
                orderA
        );
        OrderItems odetailBa = new OrderItems(
                1,
                sampleA,
                orderB
        );

        return args -> {
            category.saveAll(List.of(
                    CATagd, CATphoto
            ));
            inventory.saveAll(List.of(
                    INVsampleA, INVsampleB
            ));
            product.saveAll(List.of(
                    sampleA, sampleB
            ));
            payment.saveAll(List.of(
                    payA, payB
            ));
            order.saveAll(List.of(
                    orderA, orderB
            ));
            orderItems.saveAll(List.of(
                    odetailAa, odetailAb, odetailBa
            ));
        };
    }
}
