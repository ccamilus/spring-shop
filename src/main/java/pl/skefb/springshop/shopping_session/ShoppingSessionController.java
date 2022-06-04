package pl.skefb.springshop.shopping_session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shopping_session")
public class ShoppingSessionController {

    private final ShoppingSesionService shoppingSesionService;

    @Autowired
    public ShoppingSessionController(ShoppingSesionService shoppingSesionService) {
        this.shoppingSesionService = shoppingSesionService;
    }

    @GetMapping
    public List<ShoppingSession> getShoppingSessions() {
        return shoppingSesionService.getShoppingSessions();
    }

    @DeleteMapping(path = "{shoppingSessionID}")
    public void deleteShoppingSession(@PathVariable("shoppingSessionID") int shoppingSessionID) {
        shoppingSesionService.deleteSession(shoppingSessionID);
    }
}
