package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lngtr
 * @since 2017-12-24
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByOrderByDateAsc();
}
