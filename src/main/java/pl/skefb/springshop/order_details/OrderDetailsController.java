package pl.skefb.springshop.order_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/order")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public List<OrderDetails> getAllOrderDetails() {
        return this.orderDetailsService.getAllOrderDetails();
    }

    @GetMapping(path = "{orderId}")
    public Optional<OrderDetails> getOrderDetailsById(@PathVariable("orderId") Integer orderId) {
        return this.orderDetailsService.getOrderDetailsById(orderId);
    }

    @PostMapping
    public void addOrderDetails(@RequestBody OrderDetails orderDetails) {
        this.orderDetailsService.addOrderDetails(orderDetails);
    }

    @DeleteMapping(path = "{orderId}")
    public void deleteOrderDetailsById(@PathVariable("orderId") Integer orderId) {
        this.orderDetailsService.deleteOrderDetailById(orderId);
    }
}
