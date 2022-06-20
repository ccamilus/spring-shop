package pl.skefb.springshop.shoppingsession.cartitem;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.exception.UserNotFoundException;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingSessionService shoppingSessionService;
    private final ProductService productService;

    @Transactional
    public List<CartItem> getAllCartItemByShoppingSessionId(Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        return cartItemRepository.getCartItemsByShoppingSessionId(shoppingSession.getId());
    }

    @Transactional
    public CartItem addCartItemToCurrentShoppingSession(CartItemRequest cartItemRequest,
                                                        Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        Product product = productService.getProductById(cartItemRequest.getProductId());
        double total = shoppingSession.getTotal();
        double cartItemTotal = product.getPrice() * cartItemRequest.getQuantity();
        total += cartItemTotal;
        shoppingSession.setTotal(total);
        CartItem cartItem = new CartItem(shoppingSession, product, cartItemRequest.getQuantity(), cartItemTotal);
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void changeCartItemQuantity(Long cartItemId, Integer quantity, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = cartItemRepository.getById(cartItemId);
        double difference = (cartItem.getQuantity() - quantity) * cartItem.getProduct().getPrice();
        cartItem.setTotal(cartItem.getProduct().getPrice() * quantity);
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.changeCartItemQuantity(cartItemId, quantity, shoppingSession.getId());
    }

    @Transactional
    public void deleteCartItemById(Long cartItemId, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = cartItemRepository.getById(cartItemId);
        double difference = cartItem.getQuantity() * cartItem.getProduct().getPrice();
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.deleteCartItemById(cartItemId, shoppingSession.getId());
    }

    public boolean existsByProductId(Long id) {
        return cartItemRepository.existsByProductId(id);
    }
}
