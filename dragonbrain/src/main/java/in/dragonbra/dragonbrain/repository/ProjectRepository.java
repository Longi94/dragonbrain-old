package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lngtr
 * @since 2017-12-08
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByOrderBy();

    @Query(value = "SELECT max(order_by) FROM project", nativeQuery = true)
    Integer getMaxOrderBy();

    @Query(value = "SELECT * FROM project WHERE order_by > ?1 ORDER BY order_by ASC LIMIT 1", nativeQuery = true)
    Project getNext(int order);

    @Query(value = "SELECT * FROM project WHERE order_by < ?1 ORDER BY order_by DESC LIMIT 1", nativeQuery = true)
    Project getPrevious(int order);
}
