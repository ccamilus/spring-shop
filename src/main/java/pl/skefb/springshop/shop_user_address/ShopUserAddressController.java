package pl.skefb.springshop.shop_user_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shop_user_address")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ShopUserAddressController {

    private final ShopUserAddressService shopUserAddressService;

    @Autowired
    public ShopUserAddressController(ShopUserAddressService shopUserAddressService) {
        this.shopUserAddressService = shopUserAddressService;
    }

    @GetMapping
    public List<ShopUserAddress> getAddresses() {
        return shopUserAddressService.getAddresses();
    }

    @DeleteMapping(path = "{shopUserAddressID}")
    public void deleteAddress(@PathVariable("shopUserAddressID") int shopUserAddressID) {
        shopUserAddressService.deleteAddress(shopUserAddressID);
    }
}
