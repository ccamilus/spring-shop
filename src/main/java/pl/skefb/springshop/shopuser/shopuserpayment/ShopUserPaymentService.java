package pl.skefb.springshop.shopuser.shopuserpayment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShopUserPaymentService {
    private final ShopUserPaymentRepository shopUserPaymentRepository;

    public Optional<List<ShopUserPayment>> getShopUserPaymentsByShopUserId(Long shopUserId) {
        return shopUserPaymentRepository.getShopUserPaymentsByShopUserId(shopUserId);
    }
}
