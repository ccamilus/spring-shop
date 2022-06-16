package pl.skefb.springshop.orderdetails.orderitem;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void addOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
