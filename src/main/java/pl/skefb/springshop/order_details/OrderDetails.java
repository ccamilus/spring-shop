package pl.skefb.springshop.order_details;

import lombok.*;
import pl.skefb.springshop.payment_details.PaymentDetails;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderDetails {
    @Id
    @SequenceGenerator(
            name = "order_details_sequence",
            sequenceName = "order_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_details_sequence"
    )
    private Integer id;

    @Column(scale = 2)
    private double total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentDetails payment;

    // TODO : add user_id

    // TODO : Create 2 more TIMESTAMP variables


    public OrderDetails(double total, PaymentDetails payment) {
        this.total = total;
        this.payment = payment;
    }
}
