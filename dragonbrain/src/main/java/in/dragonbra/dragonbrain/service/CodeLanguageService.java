package in.dragonbra.dragonbrain.service;

import in.dragonbra.dragonbrain.entity.CodeLanguage;
import in.dragonbra.dragonbrain.repository.CodeLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Service
public class CodeLanguageService {

    private final CodeLanguageRepository codeLanguageRepository;

    @Autowired
    public CodeLanguageService(CodeLanguageRepository codeLanguageRepository) {
        this.codeLanguageRepository = codeLanguageRepository;
    }

    @Transactional
    public void updateLanguages(Map<String, Long> languages) {
        codeLanguageRepository.nullify();

        languages.forEach((language, count) -> {
            CodeLanguage codeLanguage = codeLanguageRepository.findByName(language);

            if (codeLanguage == null) {
                codeLanguage = new CodeLanguage();
                codeLanguage.setName(language);
            }

            codeLanguage.setCount(count);

            codeLanguageRepository.save(codeLanguage);
        });
    }
}
