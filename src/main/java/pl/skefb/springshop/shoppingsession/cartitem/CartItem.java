package pl.skefb.springshop.shoppingsession.cartitem;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.shoppingsession.ShoppingSession;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @SequenceGenerator(name = "cart_item_sequence", sequenceName = "cart_item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_sequence")
    private Long id;
    @ManyToOne
    private ShoppingSession shoppingSession;
    @OneToOne
    private Product product;
    private Integer quantity;
    private double total;

    public CartItem(ShoppingSession shoppingSession,
                    Product product,
                    Integer quantity,
                    double total) {
        this.shoppingSession = shoppingSession;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
