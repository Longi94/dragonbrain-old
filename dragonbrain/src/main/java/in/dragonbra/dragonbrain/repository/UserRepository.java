package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 2017-12-25
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRoot(boolean root);
}
