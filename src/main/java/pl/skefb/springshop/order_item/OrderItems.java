package pl.skefb.springshop.order_item;


import lombok.*;
import pl.skefb.springshop.order_details.OrderDetails;
import pl.skefb.springshop.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrderItems {
    @Id
    @SequenceGenerator(
            name = "order_items_sequence",
            sequenceName = "order_items_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_items_sequence"
    )
    private Integer id;
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDetails orderDetails;


    // TODO : remove product inventory (depends on status in payment?)
    public OrderItems(Integer quantity, Product product, OrderDetails order) {
        this.quantity = quantity;
        this.product = product;
        this.orderDetails = order;
    }
}
