package pl.skefb.springshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository
                .findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("This email is already taken!");
        }
        userRepository.save(user);
    }

    public void deleteUser(int userID) {
        boolean exists = userRepository.existsById(userID);
        if (!exists) {
            throw new IllegalStateException("User with id " + userID + " does not exist!");
        }
        userRepository.deleteById(userID);
    }

    @Transactional
    public void updateUser(int userID,
                           String username,
                           String email) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + userID + " does not exist!"));

        if (username != null &&
                username.length() > 0 &&
                !Objects.equals(user.getUsername(), username)) {
            user.setUsername(username);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository
                    .findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken!");
            }
            user.setEmail(email);
        }
    }
}
