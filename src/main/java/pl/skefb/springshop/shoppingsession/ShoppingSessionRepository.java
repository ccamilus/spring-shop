package pl.skefb.springshop.shoppingsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Long> {
    @Transactional
    @Query("SELECT ss FROM ShoppingSession ss WHERE ss.isExpired = false AND ss.shopUser.id = ?1")
    Optional<ShoppingSession> getCurrentlyActiveShoppingSession(Long shopUserId);

    @Transactional
    @Modifying
    @Query("UPDATE ShoppingSession ss SET ss.isExpired = TRUE WHERE ss.id = ?1")
    void closeCurrentShoppingSession(Long shoppingSessionId);

    @Transactional
    @Modifying
    @Query("UPDATE ShoppingSession ss SET ss.total = ?2 WHERE ss.id = ?1")
    void updateTotal(Long shoppingSessionId, BigDecimal total);
}
