package pl.skefb.springshop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Integer> {

//    @Query("SELECT s FROM shop_user s WHERE s.email = ?1")
    Optional<User> findUserByEmail(String email);


}
