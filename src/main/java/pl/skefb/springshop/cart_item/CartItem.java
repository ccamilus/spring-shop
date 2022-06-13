package pl.skefb.springshop.cart_item;


import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.shopping_session.ShoppingSession;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cart_item")
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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ShoppingSession shoppingSession;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public CartItem() {
    }

    public CartItem(Integer id, Integer quantity, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public CartItem(Integer quantity, Timestamp createdAt, Timestamp modifiedAt) {
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getQantity() {
        return quantity;
    }

    public void setQantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
