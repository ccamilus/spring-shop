package pl.skefb.springshop.shoppingsession;


import lombok.AllArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.shoppingsession.cartitem.CartItem;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemRequest;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/shopping-session")
@AllArgsConstructor
public class ShoppingSessionController {
    private final ShoppingSessionService shoppingSessionService;
    private final CartItemService cartItemService;

    @GetMapping()
    private Optional<ShoppingSession> getCurrentlyActiveShoppingSession(Authentication authentication) {
        return shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
    }

    @PutMapping("close")
    private void closeCurrentShoppingSession(Authentication authentication) {
        shoppingSessionService.closeCurrentShoppingSession(authentication);
    }

    @GetMapping("cart-items")
    private Optional<List<CartItem>> getAllCartItems(Authentication authentication) {
        return cartItemService.getAllCartItemByShoppingSessionId(authentication);
    }

    @PostMapping("cart-items/save")
    private void addCartItemToCurrentShoppingSession(@RequestBody CartItemRequest cartItemRequest,
                                                     Authentication authentication) {
        cartItemService.addCartItemToCurrentShoppingSession(cartItemRequest, authentication);
    }

    @PutMapping("cart-items/change-quantity/{cartItemId}/{quantity}")
    private void changeCartItemQuantity(@PathVariable("cartItemId") Long cartItemId,
                                        @PathVariable("quantity") Integer quantity,
                                        Authentication authentication) {
        cartItemService.changeCartItemQuantity(cartItemId, quantity, authentication);
    }

    @DeleteMapping("cart-items/delete/{cartItemId}")
    private void deleteCartItemById(@PathVariable("cartItemId") Long cartItemId, Authentication authentication) {
        cartItemService.deleteCartItemById(cartItemId, authentication);
    }
}
