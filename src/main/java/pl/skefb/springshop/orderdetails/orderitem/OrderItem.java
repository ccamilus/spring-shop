package pl.skefb.springshop.orderdetails.orderitem;


import lombok.*;
import pl.skefb.springshop.orderdetails.OrderDetails;
import pl.skefb.springshop.product.Product;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_items_sequence", sequenceName = "order_items_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_items_sequence")
    private Long id;
    @ManyToOne
    private OrderDetails orderDetails;
    @OneToOne
    private Product product;
    private Integer quantity;

    public OrderItem(OrderDetails orderDetails, Product product, Integer quantity) {
        this.orderDetails = orderDetails;
        this.product = product;
        this.quantity = quantity;
    }
}
