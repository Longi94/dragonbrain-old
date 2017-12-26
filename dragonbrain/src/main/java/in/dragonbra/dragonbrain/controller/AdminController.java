package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.entity.Project;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import in.dragonbra.dragonbrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lngtr
 * @since 2017-12-26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/projects")
    public Project createProject(Principal principal, @RequestBody Project project) {
        //userRepository.checkPrincipal(principal);
        project.setOrderBy(projectRepository.getMaxOrderBy() + 1);
        projectRepository.save(project);
        return project;
    }
}
