package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ApiRequestException;

import java.util.List;

@AllArgsConstructor
@Service
public class ShopUserPaymentService {
    private final ShopUserPaymentRepository shopUserPaymentRepository;

    public List<ShopUserPayment> getShopUserPayments(Authentication authentication) {
        return shopUserPaymentRepository.getShopUserPaymentsByShopUserEmail(authentication.getName());
    }

    public ShopUserPayment getShopUserPaymentById(Long id) {
        return shopUserPaymentRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono metody płatności o id " + id));
    }

    public void saveNewShopUserPayment(ShopUserPayment shopUserPayment) {
        shopUserPaymentRepository.save(shopUserPayment);
    }
}
