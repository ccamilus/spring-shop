package pl.skefb.springshop.shop_user_payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopUserPaymentRepository extends JpaRepository<ShopUserPayment, Integer> {
}
