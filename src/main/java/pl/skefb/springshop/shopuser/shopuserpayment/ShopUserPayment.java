package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@Entity
public class ShopUserPayment {
    @Id
    @SequenceGenerator(name = "user_payment_sequence", sequenceName = "user_payment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_payment_sequence")
    private Long id;
    @ManyToOne
    private ShopUser shopUser;
    private String cardNumber;
    private YearMonth expirationDate;
    private String cvvCode;

    public ShopUserPayment(ShopUser shopUser, String cardNumber, YearMonth expirationDate, String cvvCode) {
        this.shopUser = shopUser;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvCode = cvvCode;
    }
}
