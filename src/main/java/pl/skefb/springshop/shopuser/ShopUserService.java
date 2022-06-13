package pl.skefb.springshop.shopuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final ShopUserRepository shopUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return shopUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpUser(ShopUser shopUser) {
        boolean userExists = shopUserRepository.findByEmail(shopUser.getEmail()).isPresent();
        if (userExists) {
            // TODO: write better exception handler
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(shopUser.getPassword());
        shopUser.setPassword(encodedPassword);
        shopUserRepository.save(shopUser);
        // TODO: send confirmation token
    }

    public List<ShopUser> getUsers(){
        return shopUserRepository.findAll();
    }

    public ShopUser getUserById(Long id) {
        // TODO: write better exception handler
        return shopUserRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("user " + id + " not found"));
    }

    public void deleteUser(Long id) {
        boolean exists = shopUserRepository.existsById(id);
        if (!exists) {
            // TODO: write better exception handler
            throw new IllegalStateException("User with id " + id + " does not exist!");
        }
        shopUserRepository.deleteById(id);
    }
}
