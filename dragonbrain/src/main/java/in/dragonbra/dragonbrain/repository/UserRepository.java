package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.controller.exception.UnauthorizedException;
import in.dragonbra.dragonbrain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;

/**
 * @author lngtr
 * @since 2017-12-25
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    default User checkPrincipal(Principal principal) {
        if (principal == null) {
            throw new UnauthorizedException();
        }

        User user = findByUsername(principal.getName());

        if (user == null) {
            throw new UnauthorizedException();
        }

        return user;
    }
}
