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

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/shopping-session")
@AllArgsConstructor
public class ShoppingSessionController {
    private final ShoppingSessionService shoppingSessionService;
    private final CartItemService cartItemService;

    @GetMapping("cart-items")
    public ResponseEntity<Object> getCartItemsByShoppingSessionId(Authentication authentication) {
        List<CartItem> cartItems = cartItemService.getCartItemsByShoppingSessionId(authentication);

        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        Map<String, Object> data = new HashMap<>();
        data.put("total", shoppingSession.getTotal());
        List<Map<String, Object>> items = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", cartItem.getId());
            tmp.put("product", cartItem.getProduct());
            tmp.put("total", cartItem.getTotal());
            tmp.put("quantity", cartItem.getQuantity());
            items.add(tmp);
        }
        data.put("cart_items", items);
        return ResponseHandler.generateResponse("Sukces", HttpStatus.OK, data);
    }

    @PostMapping("cart-items/save")
    public ResponseEntity<Object> addCartItemToCurrentShoppingSession(@RequestBody CartItemRequest cartItemRequest,
                                                                      Authentication authentication) {
        if (cartItemService.existsByProductId(cartItemRequest.getProductId())) {
            return ResponseHandler
                    .generateResponseWithoutData("Produkt znajduje się w koszyku", HttpStatus.CONFLICT);
        } else if (cartItemRequest.getQuantity() < 1) {
            return ResponseHandler
                    .generateResponseWithoutData("Ilość musi być większa od 0", HttpStatus.BAD_REQUEST);
        } else {
            cartItemService.addCartItemToCurrentShoppingSession(cartItemRequest, authentication);
            return ResponseHandler
                    .generateResponseWithoutData("Produkt został dodany do koszyka", HttpStatus.CREATED);
        }
    }

    @PutMapping("cart-items/change-quantity/{cartItemId}/{quantity}")
    public ResponseEntity<Object> changeCartItemQuantity(@PathVariable("cartItemId") Long cartItemId,
                                                         @PathVariable("quantity") Integer quantity,
                                                         Authentication authentication) {
        int quantityBeforeChange = cartItemService.getById(cartItemId).getQuantity();
        if (quantity < 1) {
            return ResponseHandler
                    .generateResponseWithoutData("Ilość musi być większa od 0", HttpStatus.BAD_REQUEST);
        } else {
            cartItemService.changeCartItemQuantity(cartItemId, quantity, authentication);
            return ResponseHandler
                    .generateResponseWithoutData("Zmieniono ilość produktu z " + quantityBeforeChange + " na " +
                            quantity + " dla produktu w koszyku o id " + cartItemId, HttpStatus.OK);
        }
    }

    @DeleteMapping("cart-items/delete/{cartItemId}")
    public ResponseEntity<Object> deleteCartItemById(@PathVariable("cartItemId") Long cartItemId,
                                                     Authentication authentication) {
        cartItemService.deleteCartItemById(cartItemId, authentication);
        return ResponseHandler
                .generateResponseWithoutData("Usunięto produkt z koszyka o id " +
                        cartItemId, HttpStatus.OK);
    }
}
