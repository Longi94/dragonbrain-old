package in.dragonbra.dragonbrain.service;

import in.dragonbra.dragonbrain.entity.User;
import in.dragonbra.dragonbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author lngtr
 * @since 2017-12-25
 */
@Transactional
@Service
public class DragonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DragonUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getHash(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
