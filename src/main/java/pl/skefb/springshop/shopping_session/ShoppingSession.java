package pl.skefb.springshop.shopping_session;

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
    private int id;
    private int userId;
    private BigDecimal total;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public ShoppingSession() {
    }

    public ShoppingSession(int id, int userId, BigDecimal total, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public ShoppingSession(int userId, BigDecimal total, Timestamp createdAt, Timestamp modifiedAt) {
        this.userId = userId;
        this.total = total;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
