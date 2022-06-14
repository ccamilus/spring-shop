package pl.skefb.springshop.product;

import lombok.*;
import pl.skefb.springshop.product.productcategory.ProductCategory;
import pl.skefb.springshop.product.productinventory.ProductInventory;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
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
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(scale = 2)
    private double price;
    private String imageUrl;
    @OneToOne
    private ProductInventory productInventory;
    @ManyToOne
    private ProductCategory productCategory;

    public Product(String name,
                   String description,
                   double price,
                   String imageUrl,
                   ProductInventory productInventory,
                   ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productInventory = productInventory;
        this.productCategory = productCategory;
    }
}
