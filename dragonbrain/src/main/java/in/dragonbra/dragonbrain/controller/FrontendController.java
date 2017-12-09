package in.dragonbra.dragonbrain.controller;

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

    @Autowired
    public FrontendController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", projectRepository.findBySmall(false));
        model.addAttribute("smallProjects", projectRepository.findBySmall(true));
        return "projects";
    }

    @GetMapping("/photos")
    public String photos() {
        return "photos";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
