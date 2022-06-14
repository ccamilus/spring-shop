package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddress;
import pl.skefb.springshop.shopuser.shopuseraddress.ShopUserAddressService;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPayment;
import pl.skefb.springshop.shopuser.shopuserpayment.ShopUserPaymentService;

import java.util.List;
import java.util.Optional;

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
    public Optional<List<ShopUserAddress>> getUserAddressesByShopUserId(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserAddressService.getAddressesByUserId(shopUserId);
    }

    @GetMapping(path = "{shopUserId}/payments")
    public Optional<List<ShopUserPayment>> getShopUserPaymentsByShopUserId(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserPaymentService.getShopUserPaymentsByShopUserId(shopUserId);
    }

    @DeleteMapping(path = "{shopUserId}")
    public void deleteUser(@PathVariable("shopUserId") Long shopUserId) {
        shopUserService.deleteUser(shopUserId);
    }
}
