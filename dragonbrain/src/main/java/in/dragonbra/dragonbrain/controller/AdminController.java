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
        userService.checkPrincipal(principal);
        return projectService.createProject(project);
    }

    @PostMapping("/photos")
    public Photo createPhoto(Principal principal, @RequestBody Photo photo) {
        userService.checkPrincipal(principal);
        photoRepository.save(photo);
        return photo;
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(Principal principal, @PathVariable("id") Long id) {
        userService.checkPrincipal(principal);
        projectService.deleteProject(id);
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(Principal principal, @PathVariable("id") Long id) {
        userService.checkPrincipal(principal);
        photoRepository.deleteById(id);
    }

    @PostMapping("/projects/{id}/move")
    public void moveProject(Principal principal, @PathVariable("id") Long id, @RequestParam("up") boolean up) {
        userService.checkPrincipal(principal);
        projectService.moveProject(id, up);
    }

    @PutMapping("/projects/{id}")
    public Project editProject(Principal principal, @PathVariable("id") Long id, @RequestBody Project project) {
        userService.checkPrincipal(principal);
        return projectService.updateProject(id, project);
    }

    @GetMapping("/projects/{id}")
    public Project getProject(Principal principal, @PathVariable("id") Long id) {
        userService.checkPrincipal(principal);
        return projectService.getProject(id);
    }

    @PutMapping("/photos/{id}")
    public Photo editProject(Principal principal, @PathVariable("id") Long id, @RequestBody Photo photo) {
        userService.checkPrincipal(principal);
        Photo original = photoRepository.getOne(id);

        original.setDate(photo.getDate());
        original.setDevice(photo.getDevice());
        original.setThumbnail(photo.getThumbnail());
        original.setTitle(photo.getTitle());
        original.setPath(photo.getPath());

        return photoRepository.save(original);
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(Principal principal, @PathVariable("id") Long id) {
        userService.checkPrincipal(principal);
        return photoRepository.getOne(id);
    }
}
