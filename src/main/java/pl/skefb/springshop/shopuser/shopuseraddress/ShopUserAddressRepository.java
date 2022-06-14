package pl.skefb.springshop.shopuser.shopuseraddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopUserAddressRepository extends JpaRepository<ShopUserAddress, Integer> {
    Optional<List<ShopUserAddress>> getShopUserAddressesByShopUserId(Long shopUserId);
}
