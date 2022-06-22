package pl.skefb.springshop.shopuser.shopuserpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopUserPaymentRepository extends JpaRepository<ShopUserPayment, Long> {
    List<ShopUserPayment> getShopUserPaymentsByShopUserEmail(String email);

}
