package pl.skefb.springshop.shop_user_payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shop_payments")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ShopUserPaymentController {

    private final ShopUserPaymentService shopUserPaymentService;

    @Autowired
    public ShopUserPaymentController(ShopUserPaymentService shopUserPaymentService) {
        this.shopUserPaymentService = shopUserPaymentService;
    }

    @GetMapping
    public List<ShopUserPayment> getPayments() {
        return shopUserPaymentService.getPayments();
    }

    @DeleteMapping(path = "{shopUserPaymentID}")
    public void deletePayment(@PathVariable("shopUserPaymentID") int shopUserPaymentID) {
        shopUserPaymentService.deletePayment(shopUserPaymentID);
    }
}
