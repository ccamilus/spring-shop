package pl.skefb.springshop.product_inventory;

import lombok.*;
import pl.skefb.springshop.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductInventory {
    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Integer id;
    private Integer quantity;

    @OneToOne(
            mappedBy = "productInventory",
            cascade = CascadeType.ALL
    )
    private Product product;

    // TODO : add 3 more attrs of TIMESTAMP and update below constructor

    public ProductInventory(Integer quantity) {
        this.quantity = quantity;
    }
}
