package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.entity.Photo;
import in.dragonbra.dragonbrain.entity.Project;
import in.dragonbra.dragonbrain.repository.PhotoRepository;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import in.dragonbra.dragonbrain.repository.UserRepository;
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

    private final ProjectRepository projectRepository;

    private final PhotoRepository photoRepository;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(ProjectRepository projectRepository, PhotoRepository photoRepository,
                           UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/projects")
    public Project createProject(Principal principal, @RequestBody Project project) {
        //userRepository.checkPrincipal(principal);
        project.setOrderBy(projectRepository.getMaxOrderBy() + 1);
        projectRepository.save(project);
        return project;
    }

    @PostMapping("/photos")
    public Photo createPhoto(Principal principal, @RequestBody Photo photo) {
        //userRepository.checkPrincipal(principal);
        photoRepository.save(photo);
        return photo;
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(Principal principal, @PathVariable("id") Long id) {
        //userRepository.checkPrincipal(principal);
        projectRepository.delete(id);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(Principal principal, @PathVariable("id") Long id) {
        //userRepository.checkPrincipal(principal);
        photoRepository.delete(id);
    }
}
