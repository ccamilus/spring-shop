package pl.skefb.springshop.order_details;

import lombok.*;
import pl.skefb.springshop.order_item.OrderItems;
import pl.skefb.springshop.payment_details.PaymentDetails;
import pl.skefb.springshop.shopuser.ShopUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // TODO : automatically calculate total value
    @Column(scale = 2)
    private double total;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentDetails payment;

    @OneToMany(
            mappedBy = "orderDetails",
            cascade = CascadeType.ALL
    )
    private List<OrderItems> orderItems = new ArrayList<>();

    // TODO : add user_id

    @OneToOne
    @JoinColumn(name = "user_id")
    private ShopUser shopUser;

    // TODO : Create 2 more TIMESTAMP variables


    public OrderDetails(double total, PaymentDetails payment) {
        this.total = total;
        this.payment = payment;
    }
}
