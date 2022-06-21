package pl.skefb.springshop.registration.token;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Instant createdAt;
    private Instant confirmedAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "shop_user_id")
    private ShopUser shopUser;

    public ConfirmationToken(String token, Instant createdAt, ShopUser shopUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.shopUser = shopUser;
    }
}
