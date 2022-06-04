package pl.skefb.springshop.shopping_session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingSesionService {

    private final ShoppingSessionRepository shoppingSessionRepository;

    @Autowired
    public ShoppingSesionService(ShoppingSessionRepository shoppingSessionRepository) {
        this.shoppingSessionRepository = shoppingSessionRepository;
    }
    
    public List<ShoppingSession> getShoppingSessions() {
        return shoppingSessionRepository.findAll();
    }

    public void deleteSession(int shoppingSessionID) {
        boolean exists = shoppingSessionRepository.existsById(shoppingSessionID);
        if (!exists) {
            throw new IllegalStateException("Shopping session with id " + shoppingSessionID + " does not exist!");
        }
        shoppingSessionRepository.deleteById(shoppingSessionID);
    }
}
