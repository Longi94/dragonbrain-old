package in.dragonbra.dragonbrain.service;

import in.dragonbra.dragonbrain.controller.exception.UnauthorizedException;
import in.dragonbra.dragonbrain.entity.User;
import in.dragonbra.dragonbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * @author lngtr
 * @since 2017-12-27
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkPrincipal(Principal principal) {
        if (principal == null) {
            throw new UnauthorizedException();
        }

        User user = userRepository.findByUsername(principal.getName());

        if (user == null) {
            throw new UnauthorizedException();
        }

        return user;
    }
}
