package in.dragonbra.dragonbrain.service;

import in.dragonbra.dragonbrain.controller.exception.BadRequestException;
import in.dragonbra.dragonbrain.controller.exception.NotFoundException;
import in.dragonbra.dragonbrain.entity.Project;
import in.dragonbra.dragonbrain.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lngtr
 * @since 2017-12-27
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        project.setOrderBy(projectRepository.getMaxOrderBy() + 1);
        projectRepository.save(project);
        return project;
    }

    public void deleteProject(Long id) {
        projectRepository.delete(id);
    }

    public void moveProject(Long id, boolean up) {
        Project project = projectRepository.findOne(id);

        if (project == null) {
            throw new NotFoundException();
        }

        Project other = up ?
                projectRepository.getPrevious(project.getOrderBy()) :
                projectRepository.getNext(project.getOrderBy());

        if (other == null) {
            throw new BadRequestException();
        }

        int temp = project.getOrderBy();
        int tempOther = other.getOrderBy();

        project.setOrderBy(-1);
        projectRepository.save(project);

        other.setOrderBy(temp);
        projectRepository.save(other);

        project.setOrderBy(tempOther);
        projectRepository.save(project);
    }

    public Project updateProject(Long id, Project newProject) {
        Project project = projectRepository.findOne(id);

        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());
        project.setImage(newProject.getImage());
        project.setSourceUrl(newProject.getSourceUrl());
        project.setType(newProject.getType());
        project.setUrl(newProject.getUrl());

        return projectRepository.save(project);
    }

    public Project getProject(Long id) {
        return projectRepository.findOne(id);
    }
}