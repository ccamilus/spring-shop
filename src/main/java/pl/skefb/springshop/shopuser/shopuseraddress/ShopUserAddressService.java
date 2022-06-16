package pl.skefb.springshop.shopuser.shopuseraddress;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopUserAddressService {
    private final ShopUserAddressRepository shopUserAddressRepository;

    public List<ShopUserAddress> getAddressesByUserId(Long id) {
        return shopUserAddressRepository.getShopUserAddressesById(id);
    }

    public ShopUserAddress getShopUserAddressById(Long id) {
        return shopUserAddressRepository.getById(id);
    }
}
