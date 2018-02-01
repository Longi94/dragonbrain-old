package in.dragonbra.dragonbrain.repository;

import in.dragonbra.dragonbrain.entity.CodeLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lngtr
 * @since 2018-02-01
 */
public interface CodeLanguageRepository extends JpaRepository<CodeLanguage, Long> {

    @Modifying
    @Query(value = "UPDATE code_language cl set cl.count = 0", nativeQuery = true)
    void nullify();

    CodeLanguage findByName(String name);
}
