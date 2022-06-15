package pl.skefb.springshop.shoppingsession.cartitem;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.shoppingsession.ShoppingSession;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @SequenceGenerator(
            name = "cart_item_sequence",
            sequenceName = "cart_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_sequence"
    )
    private Long id;
    @ManyToOne
    private ShoppingSession shoppingSession;
    @OneToOne
    private Product product;
    private Integer quantity;

    public CartItem(ShoppingSession shoppingSession, Product product, Integer quantity) {
        this.shoppingSession = shoppingSession;
        this.product = product;
        this.quantity = quantity;
    }
}
