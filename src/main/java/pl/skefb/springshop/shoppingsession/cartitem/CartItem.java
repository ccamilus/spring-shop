package pl.skefb.springshop.shoppingsession.cartitem;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.shoppingsession.ShoppingSession;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
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
    @Column(scale = 2)
    private BigDecimal total;

    public CartItem(ShoppingSession shoppingSession,
                    Product product,
                    Integer quantity,
                    BigDecimal total) {
        this.shoppingSession = shoppingSession;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
}
