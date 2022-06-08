package pl.skefb.springshop.order_item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/order_items")
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping
    public List<OrderItems> getAllOrderItems() {
        return this.orderItemsService.getAllOrderItems();
    }

    @GetMapping(path = "{orderItemsId}")
    public Optional<OrderItems> getOrderItemsById(@PathVariable("orderItemsId") Integer orderItemsId) {
        return this.orderItemsService.getOrderItemsById(orderItemsId);
    }

    @PostMapping
    public void addOrderItems(OrderItems orderItems) {
        this.orderItemsService.addOrderItems(orderItems);
    }

    @DeleteMapping(path = "{orderItemsId}")
    public void deleteOrderItemsById(@PathVariable("orderItemsId") Integer orderItemsId) {
        this.orderItemsService.deleteOrderItemsById(orderItemsId);
    }
}
