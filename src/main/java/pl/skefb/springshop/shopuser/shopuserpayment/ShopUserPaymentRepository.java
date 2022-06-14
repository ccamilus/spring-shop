package pl.skefb.springshop.shopuser.shopuserpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopUserPaymentRepository extends JpaRepository<ShopUserPayment, Long> {
    Optional<List<ShopUserPayment>> getShopUserPaymentsByShopUserId(Long shopUserId);
}
