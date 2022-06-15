package pl.skefb.springshop.shoppingsession.cartitem;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingSessionService shoppingSessionService;
    private final ProductService productService;

    @Transactional
    public Optional<List<CartItem>> getAllCartItemByShoppingSessionId(Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);

        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        }

        return cartItemRepository.getCartItemsByShoppingSessionId(shoppingSession.get().getId());
    }

    @Transactional
    public void addCartItemToCurrentShoppingSession(CartItemRequest cartItemRequest,
                                                        Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);

        Optional<Product> product =
                productService.getProductById(cartItemRequest.getProductId());
        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        }
        if (product.isEmpty()) {
            throw new IllegalStateException("product not found");
        }
        CartItem cartItem = new CartItem(shoppingSession.get(), product.get(), cartItemRequest.getQuantity());
        cartItemRepository.save(cartItem);
    }

    public void changeCartItemQuantity(Long cartItemId, Integer quantity, Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        }
        cartItemRepository.changeCartItemQuantity(cartItemId, quantity, shoppingSession.get().getId());
    }

    public void deleteCartItemById(Long cartItemId, Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        }
        cartItemRepository.deleteCartItemById(cartItemId, shoppingSession.get().getId());
    }
}
