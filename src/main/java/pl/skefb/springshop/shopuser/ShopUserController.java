package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shop-users")
@AllArgsConstructor
public class ShopUserController {
    private final ShopUserService shopUserService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopUserPaymentService shopUserPaymentService;

    @GetMapping
    public List<ShopUser> getUsers() {
        return shopUserService.getUsers();
    }

    @GetMapping(path = "{shopUserId}")
    public ShopUser getUserById(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserService.getUserById(shopUserId);
    }

    @GetMapping(path = "{shopUserId}/addresses")
    public List<ShopUserAddress> getUserAddressesByShopUserId(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserAddressService.getAddressesByUserId(shopUserId);
    }

    @GetMapping(path = "{shopUserId}/payments")
    public List<ShopUserPayment> getShopUserPaymentsByShopUserId(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserPaymentService.getShopUserPaymentsByShopUserId(shopUserId);
    }

    @DeleteMapping(path = "{shopUserId}")
    public void deleteUser(@PathVariable("shopUserId") Long shopUserId) {
        shopUserService.deleteUser(shopUserId);
    }
}
