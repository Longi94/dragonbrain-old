package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.controller.dto.UserDto;
import in.dragonbra.dragonbrain.repository.PhotoRepository;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import in.dragonbra.dragonbrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Controller
public class FrontendController {

    private final ProjectRepository projectRepository;

    private final PhotoRepository photoRepository;

    private final UserService userService;

    @Autowired
    public FrontendController(ProjectRepository projectRepository,
                              PhotoRepository photoRepository,
                              UserService userService) {
        this.projectRepository = projectRepository;
        this.photoRepository = photoRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", projectRepository.findAllByOrderByOrderBy());
        return "projects";
    }

    @GetMapping("/photos")
    public String photos(Model model) {
        model.addAttribute("photos", photoRepository.findAllByOrderByDateAsc());
        return "photos";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("projects", projectRepository.findAllByOrderByOrderBy());
        model.addAttribute("photos", photoRepository.findAllByOrderByDateAsc());
        return "admin";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("registered", userService.rootRegistered());
        return "login";
    }

    @PostMapping("/register")
    public String register(Principal principal, @ModelAttribute UserDto userDto) {
        userService.registerUser(userDto, principal);
        return "redirect:login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
