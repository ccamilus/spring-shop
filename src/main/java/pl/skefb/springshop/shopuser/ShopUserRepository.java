package pl.skefb.springshop.shopuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {
    Optional<ShopUser> findByEmail(String email);
}
