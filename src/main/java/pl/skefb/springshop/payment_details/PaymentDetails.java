package pl.skefb.springshop.payment_details;

import lombok.*;
import pl.skefb.springshop.order_details.OrderDetails;

import javax.persistence.*;

@Entity
@Table(name = "payment_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class PaymentDetails {
    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Integer id;
    private Integer amount;
    private String provider;

    // TODO : create enum STATUS
    private String status;

    @OneToOne(
            mappedBy = "payment",
            cascade = CascadeType.ALL
    )
    private OrderDetails orderDetails;


    public PaymentDetails(Integer amount, String provider, String status) {
        this.amount = amount;
        this.provider = provider;
        this.status = status;
    }
}
