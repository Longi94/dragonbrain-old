package in.dragonbra.dragonbrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Controller
public class FrontendController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/projects")
    public String projects() {
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
