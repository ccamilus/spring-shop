package pl.skefb.springshop.payment_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    public List<PaymentDetails> getAllPayments() {
        return this.paymentDetailsRepository.findAll();
    }

    public Optional<PaymentDetails> getPaymentById(Integer paymentId) {
        return this.paymentDetailsRepository.findById(paymentId);
    }

    public void addPayment(PaymentDetails payment) {
        this.paymentDetailsRepository.save(payment);
    }

    // TODO : create put payment

    public void deletePaymentById(Integer paymentId) {
        boolean notExist = ! this.paymentDetailsRepository.existsById(paymentId);

        if ( notExist )
            throw new IllegalStateException("payment does not exist!");

        this.paymentDetailsRepository.deleteById(paymentId);
    }
}
