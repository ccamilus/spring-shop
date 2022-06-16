package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShopUserPaymentService {
    private final ShopUserPaymentRepository shopUserPaymentRepository;

    public List<ShopUserPayment> getShopUserPaymentsByShopUserId(Long id) {
        return shopUserPaymentRepository.getShopUserPaymentsById(id);
    }

    public ShopUserPayment getShopUserPaymentById(Long id) {
        return shopUserPaymentRepository.getById(id);
    }
}
