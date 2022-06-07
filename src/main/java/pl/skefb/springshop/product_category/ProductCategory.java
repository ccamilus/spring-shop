package pl.skefb.springshop.product_category;

import lombok.*;
import pl.skefb.springshop.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductCategory {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private Integer id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(
            mappedBy = "productCategory",
            cascade = CascadeType.ALL
    )
    private List<Product> products = new ArrayList<>();

    // TODO : add 3 more attrs of TIMESTAMP and update below constructor

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
