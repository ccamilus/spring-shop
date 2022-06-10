package pl.skefb.springshop.shopping_session;

import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table (name = "shopping_session")
public class ShoppingSession {
    @Id
    @SequenceGenerator(
            name = "shopping_session_sequence",
            sequenceName = "shopping_session_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shopping_session_sequence"
    )
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal total;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public ShoppingSession() {
    }

    public ShoppingSession(Integer id, BigDecimal total, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public ShoppingSession(BigDecimal total, Timestamp createdAt, Timestamp modifiedAt) {
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "ShoppingSession{" +
                "id=" + id +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
