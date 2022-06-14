package pl.skefb.springshop.shopuser.shopuseraddress;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopUserAddressService {
    private final ShopUserAddressRepository shopUserAddressRepository;

    public Optional<List<ShopUserAddress>> getAddressesByUserId(Long shopUserId) {
        return shopUserAddressRepository.getShopUserAddressesByShopUserId(shopUserId);
    }
}
