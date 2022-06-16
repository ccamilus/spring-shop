package pl.skefb.springshop.shoppingsession;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserRepository;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingSessionService {
    private final ShoppingSessionRepository shoppingSessionRepository;
    private final ShopUserRepository shopUserRepository;

    @Transactional
    public Optional<ShoppingSession> getCurrentlyActiveShoppingSession(Authentication authentication) {
        String email = authentication.getName();
        Optional<ShopUser> shopUser = shopUserRepository.findByEmail(email);
        if (shopUser.isEmpty()) {
            throw new IllegalStateException("user not found");
        }

        Optional<ShoppingSession> currentShoppingSession =
                shoppingSessionRepository.getCurrentlyActiveShoppingSession(shopUser.get().getId());

        if (currentShoppingSession.isEmpty()) {
            ShoppingSession shoppingSession = new ShoppingSession(
                    shopUser.get(),
                    0,
                    Instant.now()
            );
            shoppingSessionRepository.save(shoppingSession);
            return Optional.of(shoppingSession);
        }
        return currentShoppingSession;
    }

    @Transactional
    public void closeCurrentShoppingSession(Authentication authentication) {
        String email = authentication.getName();
        Optional<ShopUser> shopUser = shopUserRepository.findByEmail(email);
        if (shopUser.isEmpty()) {
            throw new IllegalStateException("user not found");
        }

        Optional<ShoppingSession> currentShoppingSession =
                shoppingSessionRepository.getCurrentlyActiveShoppingSession(shopUser.get().getId());

        currentShoppingSession.ifPresent(shoppingSession ->
                shoppingSessionRepository.closeCurrentShoppingSession(shoppingSession.getId()));
    }

    public void updateTotalByDifference(Authentication authentication, Double difference) {
        String email = authentication.getName();
        Optional<ShopUser> shopUser = shopUserRepository.findByEmail(email);
        if (shopUser.isEmpty()) {
            throw new IllegalStateException("user not found");
        }

        Optional<ShoppingSession> currentShoppingSession =
                shoppingSessionRepository.getCurrentlyActiveShoppingSession(shopUser.get().getId());

        currentShoppingSession.ifPresent(shoppingSession ->
                shoppingSessionRepository.updateTotal(shoppingSession.getId(),
                        shoppingSession.getTotal() - difference));
    }
}
