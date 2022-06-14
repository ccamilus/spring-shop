package pl.skefb.springshop.shopuser.shopuserpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopUserPaymentRepository extends JpaRepository<ShopUserPayment, Integer> {
}
