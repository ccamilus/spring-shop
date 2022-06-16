package pl.skefb.springshop.shoppingsession;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
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
    private double total;
    private Instant createdAt;
    boolean isExpired = false;

    public ShoppingSession(ShopUser shopUser, double total, Instant createdAt) {
        this.shopUser = shopUser;
        this.total = total;
        this.createdAt = createdAt;
    }
}
