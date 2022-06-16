package pl.skefb.springshop.shopuser.shopuseraddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopUserAddressRepository extends JpaRepository<ShopUserAddress, Long> {
    List<ShopUserAddress> getShopUserAddressesById(Long id);
}
