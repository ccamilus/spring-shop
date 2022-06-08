package pl.skefb.springshop.order_details;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return this.orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> getOrderDetailsById(Integer orderId) {
        return this.orderDetailsRepository.findById(orderId);
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        this.orderDetailsRepository.save(orderDetails);
    }

    public void deleteOrderDetailById(Integer orderId) {
        boolean notExist = ! this.orderDetailsRepository.existsById(orderId);

        if ( notExist )
            throw new IllegalStateException("Order Detail id:" + orderId + " does not exist!");

        this.orderDetailsRepository.deleteById(orderId);
    }
}
