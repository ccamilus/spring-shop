package pl.skefb.springshop.product;

import lombok.*;
import pl.skefb.springshop.product_category.ProductCategory;
import pl.skefb.springshop.product_inventory.ProductInventory;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private ProductInventory productInventory;

    // TODO : add 3 more attrs of TIMESTAMP and update below constructor

    public Product(String name, String description
                    , String SKU, double price
                    , ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.SKU = SKU;
        this.price = price;
        this.productCategory = productCategory;
    }
}
