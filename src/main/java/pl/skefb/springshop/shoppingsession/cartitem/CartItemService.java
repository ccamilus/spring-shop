package pl.skefb.springshop.shoppingsession.cartitem;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;

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
        double total = shoppingSession.get().getTotal();
        total += product.get().getPrice() * cartItemRequest.getQuantity();
        shoppingSession.get().setTotal(total);
        CartItem cartItem = new CartItem(shoppingSession.get(), product.get(), cartItemRequest.getQuantity());
        cartItemRepository.save(cartItem);
    }

    @Transactional
    public void changeCartItemQuantity(Long cartItemId, Integer quantity, Authentication authentication) {
        ShoppingSession shoppingSession = getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = getCartItemById(cartItemId);
        double difference = (cartItem.getQuantity() - quantity) * cartItem.getProduct().getPrice();
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.changeCartItemQuantity(cartItemId, quantity, shoppingSession.getId());
    }

    @Transactional
    public void deleteCartItemById(Long cartItemId, Authentication authentication) {
        ShoppingSession shoppingSession = getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = getCartItemById(cartItemId);
        double difference = cartItem.getQuantity() * cartItem.getProduct().getPrice();
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.deleteCartItemById(cartItemId, shoppingSession.getId());
    }

    private ShoppingSession getCurrentlyActiveShoppingSession(Authentication authentication) {
        Optional<ShoppingSession> shoppingSession =
                shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        if (shoppingSession.isEmpty()) {
            throw new IllegalStateException("shopping session not found");
        } else {
            return shoppingSession.get();
        }
    }

    private CartItem getCartItemById(Long cartItemId) {
        Optional<CartItem> cartItem = cartItemRepository.getCartItemById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new IllegalStateException("cart item not found");
        } else {
            return cartItem.get();
        }
    }
}
