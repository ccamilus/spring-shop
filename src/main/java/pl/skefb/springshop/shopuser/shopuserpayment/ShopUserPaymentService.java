package pl.skefb.springshop.shopuser.shopuserpayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopUserPaymentService {

    private final ShopUserPaymentRepository shopUserPaymentRepository;

    @Autowired
    public ShopUserPaymentService(ShopUserPaymentRepository shopUserPaymentRepository) {
        this.shopUserPaymentRepository = shopUserPaymentRepository;
    }

    public List<ShopUserPayment> getPayments() {
        return shopUserPaymentRepository.findAll();
    }

    public void deletePayment (int shopUserPaymentID) {
        boolean exists = shopUserPaymentRepository.existsById(shopUserPaymentID);
        if(!exists) {
            throw new IllegalStateException("Payment with id " + shopUserPaymentID + " does not exist!");
        }
        shopUserPaymentRepository.deleteById(shopUserPaymentID);
    }
}
