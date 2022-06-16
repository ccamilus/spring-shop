package pl.skefb.springshop.product.productcategory;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class ProductCategory {
    @Id
    @SequenceGenerator(name = "product_category_sequence", sequenceName = "product_category_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_sequence")
    public Long id;
    public String name;
    public String description;

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
