package pl.skefb.springshop.shopuser.shopuseraddress;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.skefb.springshop.exception.ApiRequestException;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopUserAddressService {
    private final ShopUserAddressRepository shopUserAddressRepository;

    public List<ShopUserAddress> getShopUserAddresses(Authentication authentication) {
        return shopUserAddressRepository.getShopUserAddressesByShopUserEmail(authentication.getName());
    }

    public ShopUserAddress getShopUserAddressById(Long id) {
        return shopUserAddressRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono adresu o id " + id));
    }

    public void saveNewShopUserAddress(ShopUserAddress shopUserAddress) {
        shopUserAddressRepository.save(shopUserAddress);
    }
}
