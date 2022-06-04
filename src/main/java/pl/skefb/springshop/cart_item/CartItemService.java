package pl.skefb.springshop.cart_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
    
    List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }
    
    public void addNewCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
    
    public void deleteCartItem(int cartItemID) {
        boolean exists = cartItemRepository.existsById(cartItemID);
        if (!exists) {
            throw new IllegalStateException("Cart item with id " + cartItemID + " does not exist!");
        }
        cartItemRepository.deleteById(cartItemID);
    }
}
