package pl.skefb.springshop.product;

import lombok.*;
import pl.skefb.springshop.order_item.OrderItems;
import pl.skefb.springshop.product_category.ProductCategory;
import pl.skefb.springshop.product_inventory.ProductInventory;

import javax.persistence.*;

// TODO : apply validation

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Integer id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String SKU;

    @Column(scale = 2)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private ProductInventory productInventory;

    @OneToOne(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private OrderItems orderItems;

    // TODO : add 3 more attrs of TIMESTAMP and update below constructor

    // TODO : add availability to passing Integer QUANTITY to replace productInventory, create PI inside constructor and delete from DB inside destructor

    public Product(String name, String description,
                   String SKU, double price,
                   ProductCategory productCategory,
                   ProductInventory productInventory) {
        this.name = name;
        this.description = description;
        this.SKU = SKU;
        this.price = price;
        this.productCategory = productCategory;
        this.productInventory = productInventory;
    }
}
