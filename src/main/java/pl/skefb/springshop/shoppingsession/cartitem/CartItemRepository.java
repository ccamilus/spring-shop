package pl.skefb.springshop.shoppingsession.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.shoppingsession.ShoppingSession;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Transactional
    @Query("SELECT ci FROM CartItem ci WHERE ci.shoppingSession.id = ?1 ORDER BY ci.id")
    List<CartItem> getCartItemsByShoppingSessionId(Long shoppingSessionId);

    @Transactional
    @Modifying
    @Query("UPDATE CartItem ci SET ci.quantity = ?2 WHERE ci.id = ?1 AND ci.shoppingSession.id = ?3")
    void changeCartItemQuantity(Long cartItemId, Integer quantity, Long shoppingSessionId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.id = ?1 AND ci.shoppingSession.id = ?2")
    void deleteCartItemById(Long cartItemId, Long shoppingSessionId);

    boolean existsByProductId(Long id);
}
