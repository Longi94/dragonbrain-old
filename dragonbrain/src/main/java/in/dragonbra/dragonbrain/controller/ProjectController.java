package in.dragonbra.dragonbrain.controller;

import in.dragonbra.dragonbrain.entity.Project;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("")
    public List<Project> getProjects() {
        return projectRepository.findAllByOrderByOrderBy();
    }
}
