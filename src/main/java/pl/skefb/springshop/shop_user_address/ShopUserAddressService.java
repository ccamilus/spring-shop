package pl.skefb.springshop.shop_user_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopUserAddressService {

    private final ShopUserAddressRepository shopUserAddressRepository;

    @Autowired
    public ShopUserAddressService(ShopUserAddressRepository shopUserAddressRepository) {
        this.shopUserAddressRepository = shopUserAddressRepository;
    }

    public List<ShopUserAddress> getAddresses() {
        return shopUserAddressRepository.findAll();
    }

    public void deleteAddress(int shopUserAddressID) {
        boolean exists = shopUserAddressRepository.existsById(shopUserAddressID);
        if (!exists) {
            throw new IllegalStateException("Address with id " + shopUserAddressID + " does not exist!");
        }
        shopUserAddressRepository.deleteById(shopUserAddressID);
    }

}
