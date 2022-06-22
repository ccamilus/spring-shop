package pl.skefb.springshop.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.skefb.springshop.response.ResponseHandler;

@RestController
@RequestMapping(path = "api/v1/orders")
@AllArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping("make-order")
    private ResponseEntity<Object> makeOrder(@RequestBody OrderDetailsRequest orderDetailsRequest, Authentication authentication) {
        if (orderDetailsRequest.getShopUserPaymentId() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano id metody płatności", HttpStatus.BAD_REQUEST);
        }
        if (orderDetailsRequest.getShopUserAddressId() == null) {
            return ResponseHandler
                    .generateResponseWithoutData("Nie podano id adresu", HttpStatus.BAD_REQUEST);
        }
        orderDetailsService.makeOrder(orderDetailsRequest, authentication);
        return ResponseHandler
                .generateResponseWithoutData("Sukces", HttpStatus.CREATED);
    }
}
