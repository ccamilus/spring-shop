package pl.skefb.springshop.cart_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cart_item")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartItemService.getCartItems();
    }

    @DeleteMapping(path = "{cartItemID}")
    public void deleteCartItem(@PathVariable("cartItemID") int cartItemID) {
        cartItemService.deleteCartItem(cartItemID);
    }
}
