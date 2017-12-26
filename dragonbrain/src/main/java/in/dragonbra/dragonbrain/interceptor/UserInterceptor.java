package in.dragonbra.dragonbrain.interceptor;

import in.dragonbra.dragonbrain.entity.User;
import in.dragonbra.dragonbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lngtr
 * @since 2017-12-25
 */
@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    private final UserRepository userRepository;

    @Value("${env}")
    private String env;

    private User rootUser;

    @Autowired
    public UserInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;

        if (!"live".equals(env)) {
            rootUser = new User();

            rootUser.setRoot(true);
            rootUser.setUsername("admin");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || modelAndView.getViewName().startsWith("redirect:/")) {
            // TODO: 2017-11-11 object added to the redirect views end up in the url parameters for some reason, there must be a better way to solve this
            return;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {

            if (!"live".equals(env) && "admin".equals(auth.getName())) {
                modelAndView.addObject("authenticatedUser", rootUser);
            } else {
                User user = userRepository.findByUsername(auth.getName());
                modelAndView.addObject("authenticatedUser", user);
            }
        }

        // TODO: 2017-12-25 remove
        modelAndView.addObject("authenticatedUser", rootUser);
    }
}
