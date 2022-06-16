package pl.skefb.springshop.orderdetails.orderitem;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void addOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
