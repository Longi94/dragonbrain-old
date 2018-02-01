package in.dragonbra.dragonbrain.service;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Service
public class GithubService {

    private final RepositoryService repositoryService;

    @Autowired
    public GithubService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Map<String, Long> getLanguages() throws IOException {
        List<Repository> repositories = repositoryService.getRepositories();

        Map<String, Long> totalLanguages = new HashMap<>();

        for (Repository repository : repositories) {
            Map<String, Long> languages = repositoryService.getLanguages(repository);

            languages.forEach((key, value) -> {
                if (totalLanguages.containsKey(key)) {
                    totalLanguages.put(key, totalLanguages.get(key) + value);
                } else {
                    totalLanguages.put(key, value);
                }
            });
        }

        return totalLanguages;
    }
}
