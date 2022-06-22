package pl.skefb.springshop.shoppingsession;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class ShoppingSession {
    @Id
    @SequenceGenerator(name = "shopping_session_sequence", sequenceName = "shopping_session_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_session_sequence")
    private Long id;
    @ManyToOne
    private ShopUser shopUser;
    @Column(scale = 2)
    private BigDecimal total;
    private Instant createdAt;
    boolean isExpired = false;

    public ShoppingSession(ShopUser shopUser, BigDecimal total, Instant createdAt) {
        this.shopUser = shopUser;
        this.total = total;
        this.createdAt = createdAt;
    }
}
