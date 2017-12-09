package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 2017-12-08
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findBySmall(boolean small);
}
