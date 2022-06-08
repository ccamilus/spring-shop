package pl.skefb.springshop.order_item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    // TODO : add GET all order items where order id equals to smthg

    public Optional<OrderItems> getOrderItemsById(Integer orderItemsId) {
        return this.orderItemsRepository.findById(orderItemsId);
    }

    public void addOrderItems(OrderItems orderItems) {
        this.orderItemsRepository.save(orderItems);
    }

    // TODO : add put method

    public void deleteOrderItemsById(Integer orderItemsId) {
        boolean notExist = ! this.orderItemsRepository.existsById(orderItemsId);

        if ( notExist )
            throw new IllegalStateException("order items does not exists!");

        orderItemsRepository.deleteById(orderItemsId);
    }
}
