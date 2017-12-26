package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.repository.PhotoRepository;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Controller
public class FrontendController {

    private final ProjectRepository projectRepository;

    private final PhotoRepository photoRepository;

    @Autowired
    public FrontendController(ProjectRepository projectRepository, PhotoRepository photoRepository) {
        this.projectRepository = projectRepository;
        this.photoRepository = photoRepository;
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
        model.addAttribute("photos", photoRepository.findAllByOrderByOrderBy());
        return "photos";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("projects", projectRepository.findAllByOrderByOrderBy());
        model.addAttribute("photos", photoRepository.findAllByOrderByOrderBy());
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
