package pl.skefb.springshop.shoppingsession.cartitem;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.exception.ApiRequestException;
import pl.skefb.springshop.product.Product;
import pl.skefb.springshop.product.ProductService;
import pl.skefb.springshop.shoppingsession.ShoppingSession;
import pl.skefb.springshop.shoppingsession.ShoppingSessionService;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingSessionService shoppingSessionService;
    private final ProductService productService;

    @Transactional
    public List<CartItem> getCartItemsByShoppingSessionId(Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        return cartItemRepository.getCartItemsByShoppingSessionId(shoppingSession.getId());
    }

    @Transactional
    public void addCartItemToCurrentShoppingSession(CartItemRequest cartItemRequest, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        Product product = productService.getProductById(cartItemRequest.getProductId());
        if (product.getProductInventory().getQuantity() < cartItemRequest.getQuantity()) {
            throw new ApiRequestException("Nie wystarczająca ilość produktu w magazynie. Aktualna ilość " +
                    product.getProductInventory().getQuantity() + ", żądana ilość " + cartItemRequest.getQuantity());
        }
        BigDecimal total = shoppingSession.getTotal();
        BigDecimal cartItemTotal = product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity()));
        total = total.add(cartItemTotal);
        shoppingSession.setTotal(total);
        CartItem cartItem = new CartItem(shoppingSession, product, cartItemRequest.getQuantity(), cartItemTotal);
        cartItemRepository.save(cartItem);
    }

    @Transactional
    public void changeCartItemQuantity(Long cartItemId, Integer quantity, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = getById(cartItemId);
        if (cartItem.getProduct().getProductInventory().getQuantity() < quantity) {
            throw new ApiRequestException("Nie wystarczająca ilość produktu w magazynie. Aktualna ilość " +
                    cartItem.getProduct().getProductInventory().getQuantity() + ", żądana ilość " + quantity);
        }
        BigDecimal difference = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity() - quantity));
        cartItem.setTotal(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.changeCartItemQuantity(cartItemId, quantity, shoppingSession.getId());
    }

    @Transactional
    public void deleteCartItemById(Long cartItemId, Authentication authentication) {
        ShoppingSession shoppingSession = shoppingSessionService.getCurrentlyActiveShoppingSession(authentication);
        CartItem cartItem = getById(cartItemId);
        BigDecimal difference = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        shoppingSessionService.updateTotalByDifference(authentication, difference);
        cartItemRepository.deleteCartItemById(cartItemId, shoppingSession.getId());
    }

    public boolean existsByProductId(Long id) {
        return cartItemRepository.existsByProductId(id);
    }

    public CartItem getById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Nie znaleziono produktu w koszyku o id " + id));
    }
}
