package pl.skefb.springshop.cart_item;


import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @SequenceGenerator(
            name = "cart_tem_sequence",
            sequenceName = "cart_tem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_tem_sequence"
    )
    private int id;
    private int sessionId;
    private int productId;
    private int qantity;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public CartItem() {
    }

    public CartItem(int id, int sessionId, int productId, int qantity, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.productId = productId;
        this.qantity = qantity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public CartItem(int sessionId, int productId, int qantity, Timestamp createdAt, Timestamp modifiedAt) {
        this.sessionId = sessionId;
        this.productId = productId;
        this.qantity = qantity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQantity() {
        return qantity;
    }

    public void setQantity(int qantity) {
        this.qantity = qantity;
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
