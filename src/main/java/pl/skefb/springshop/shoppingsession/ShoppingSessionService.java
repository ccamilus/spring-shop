package pl.skefb.springshop.shoppingsession;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.skefb.springshop.shopuser.ShopUser;
import pl.skefb.springshop.shopuser.ShopUserService;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingSessionService {
    private final ShoppingSessionRepository shoppingSessionRepository;
    private final ShopUserService shopUserService;

    @Transactional
    public ShoppingSession getCurrentlyActiveShoppingSession(Authentication authentication) {
        String email = authentication.getName();
        ShopUser shopUser = shopUserService.findByEmail(email);
        Optional<ShoppingSession> currentShoppingSession =
                shoppingSessionRepository.getCurrentlyActiveShoppingSession(shopUser.getId());
        if (currentShoppingSession.isPresent()) {
            return currentShoppingSession.get();
        } else {
            ShoppingSession shoppingSession = new ShoppingSession(shopUser, 0, Instant.now());
            shoppingSessionRepository.save(shoppingSession);
            return shoppingSession;
        }
    }

    @Transactional
    public void closeCurrentShoppingSession(Authentication authentication) {
        ShoppingSession shoppingSession = getCurrentlyActiveShoppingSession(authentication);
        shoppingSessionRepository.closeCurrentShoppingSession(shoppingSession.getId());
    }

    public void updateTotalByDifference(Authentication authentication, Double difference) {
        ShoppingSession shoppingSession = getCurrentlyActiveShoppingSession(authentication);
        shoppingSessionRepository.updateTotal(shoppingSession.getId(), shoppingSession.getTotal() - difference);
    }
}
