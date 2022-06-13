package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shop-users")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@AllArgsConstructor
public class ShopUserController {
    private final ShopUserService shopUserService;

    @GetMapping
    public List<ShopUser> getUsers() {
        return shopUserService.getUsers();
    }

    @GetMapping(path = "{shopUserId}")
    public ShopUser getUserById(@PathVariable("shopUserId") Long shopUserId) {
        return shopUserService.getUserById(shopUserId);
    }

    @DeleteMapping(path = "{shopUserId}")
    public void deleteUser(@PathVariable("shopUserId") Long shopUserId) {
        shopUserService.deleteUser(shopUserId);
    }
}
