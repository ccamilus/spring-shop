package pl.skefb.springshop.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/orders")
@AllArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping("make-order")
    private void makeOrder(@RequestBody OrderDetailsRequest orderDetailsRequest,
                           Authentication authentication) {
        orderDetailsService.makeOrder(orderDetailsRequest, authentication);
    }
}
