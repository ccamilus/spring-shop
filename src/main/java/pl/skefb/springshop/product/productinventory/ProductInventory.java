package pl.skefb.springshop.product.productinventory;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ProductInventory {
    @Id
    @SequenceGenerator(
            name = "product_inventory_sequence",
            sequenceName = "product_inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_inventory_sequence"
    )
    public Long id;
    public int quantity;

    public ProductInventory(int quantity) {
        this.quantity = quantity;
    }
}
