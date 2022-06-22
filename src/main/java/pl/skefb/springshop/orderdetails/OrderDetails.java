package pl.skefb.springshop.orderdetails;

import lombok.*;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class OrderDetails {
    @Id
    @SequenceGenerator(name = "order_details_sequence", sequenceName = "order_details_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_sequence")
    private Long id;
    @OneToOne
    private ShopUser shopUser;
    private BigDecimal total;
    @OneToOne
    private ShopUserAddress shopUserAddress;
    @OneToOne
    private ShopUserPayment shopUserPayment;
    private Instant createdAt;

    public OrderDetails(ShopUser shopUser,
                        BigDecimal total,
                        ShopUserAddress shopUserAddress,
                        ShopUserPayment shopUserPayment,
                        Instant createdAt) {
        this.shopUser = shopUser;
        this.total = total;
        this.shopUserAddress = shopUserAddress;
        this.shopUserPayment = shopUserPayment;
        this.createdAt = createdAt;
    }
}
