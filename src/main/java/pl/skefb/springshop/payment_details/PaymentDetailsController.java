package pl.skefb.springshop.payment_details;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/payment_details")
public class PaymentDetailsController {
    private final PaymentDetailsService paymentDetailsService;

    @Autowired
    public PaymentDetailsController(PaymentDetailsService paymentDetailsService) {
        this.paymentDetailsService = paymentDetailsService;
    }

    @GetMapping
    public List<PaymentDetails> getAllPayments() {
        return this.paymentDetailsService.getAllPayments();
    }

    @GetMapping(path = "{paymentId}")
    public Optional<PaymentDetails> getPaymentById(@PathVariable("paymentId") Integer paymentId) {
        return this.paymentDetailsService.getPaymentById(paymentId);
    }

    @PostMapping
    public void addPayment(@RequestBody PaymentDetails payment) {
        this.paymentDetailsService.addPayment(payment);
    }

    @DeleteMapping(path = "{paymentId}")
    public void deletePaymentById(@PathVariable("paymentId") Integer paymentId) {
        this.paymentDetailsService.deletePaymentById(paymentId);
    }
}
