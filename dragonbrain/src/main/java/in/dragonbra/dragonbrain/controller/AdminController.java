package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.entity.Photo;
import in.dragonbra.dragonbrain.entity.Project;
import in.dragonbra.dragonbrain.repository.PhotoRepository;
import in.dragonbra.dragonbrain.service.ProjectService;
import in.dragonbra.dragonbrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author lngtr
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProjectService projectService;

    private final PhotoRepository photoRepository;

    private final UserService userService;

    @Autowired
    public AdminController(ProjectService projectService, PhotoRepository photoRepository,
                           UserService userService) {
        this.projectService = projectService;
        this.photoRepository = photoRepository;
        this.userService = userService;
    }

    @PostMapping("/projects")
    public Project createProject(Principal principal, @RequestBody Project project) {
        //userService.checkPrincipal(principal);
        return projectService.createProject(project);
    }

    @PostMapping("/photos")
    public Photo createPhoto(Principal principal, @RequestBody Photo photo) {
        //userRepository.checkPrincipal(principal);
        photoRepository.save(photo);
        return photo;
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(Principal principal, @PathVariable("id") Long id) {
        //userService.checkPrincipal(principal);
        projectService.deleteProject(id);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(Principal principal, @PathVariable("id") Long id) {
        //userService.checkPrincipal(principal);
        photoRepository.delete(id);
    }

    @PostMapping("/projects/{id}/move")
    public void moveProject(Principal principal, @PathVariable("id") Long id, @RequestParam("up") boolean up) {
        //userService.checkPrincipal(principal);
        projectService.moveProject(id, up);
    }
}
