package pl.skefb.springshop.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/orders")
@AllArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping("make-order")
    private void makeOrder(@RequestBody OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
        orderDetailsService.makeOrder(orderDetailsRequest, authentication);
    }
}
