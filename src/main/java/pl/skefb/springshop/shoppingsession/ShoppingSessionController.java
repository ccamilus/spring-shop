package pl.skefb.springshop.shoppingsession;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.skefb.springshop.response.ResponseHandler;
import pl.skefb.springshop.shoppingsession.cartitem.CartItem;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemRequest;
import pl.skefb.springshop.shoppingsession.cartitem.CartItemService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shopping-session")
@AllArgsConstructor
public class ShoppingSessionController {
    private final ShoppingSessionService shoppingSessionService;
    private final CartItemService cartItemService;

    @GetMapping()
    private ShoppingSession getCurrentlyActiveShoppingSession(Authentication authentication) {
        return shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
    }

    @PutMapping("close")
    private void closeCurrentShoppingSession(Authentication authentication) {
        shoppingSessionService.closeCurrentShoppingSession(authentication);
    }

    @GetMapping("cart-items")
    private List<CartItem> getAllCartItems(Authentication authentication) {
        return cartItemService.getAllCartItemByShoppingSessionId(authentication);
    }

    @PostMapping("cart-items/save")
    private ResponseEntity<Object> addCartItemToCurrentShoppingSession(@RequestBody CartItemRequest cartItemRequest,
                                                               Authentication authentication) {
        if (cartItemService.existsByProductId(cartItemRequest.getProductId())) {
            return ResponseHandler
                    .generateResponse("Produkt znajduje się w koszyku!", HttpStatus.CONFLICT, null);
        } else {
            CartItem cartItem = cartItemService.addCartItemToCurrentShoppingSession(cartItemRequest, authentication);
            return ResponseHandler
                    .generateResponse("Produkt został dodany do koszyka!", HttpStatus.CREATED, cartItem);
        }
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
