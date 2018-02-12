package in.dragonbra.dragonbrain.service;

import com.google.gson.Gson;
import in.dragonbra.dragonbrain.model.GithubRepository;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Service
public class GithubService {

    private final RepositoryService repositoryService;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final Gson gson = new Gson();

    @Autowired
    public GithubService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public void getLanguages() throws IOException {
        List<Repository> repositories = repositoryService.getRepositories();

        Map<String, Long> totalLanguages = new HashMap<>();
        List<GithubRepository> githubRepositories = new LinkedList<>();

        for (Repository repository : repositories) {
            Map<String, Long> languages = repositoryService.getLanguages(repository);

            languages.forEach((key, value) -> {
                if (totalLanguages.containsKey(key)) {
                    totalLanguages.put(key, totalLanguages.get(key) + value);
                } else {
                    totalLanguages.put(key, value);
                }
            });

            githubRepositories.add(new GithubRepository(
                    languages,
                    repository.getId(),
                    repository.getUpdatedAt().getTime()
            ));
        }

        String date = FORMAT.format(new Date());
        String fileName = date + ".json";

        saveTotalLanguages(fileName, totalLanguages);
        saveRepositories(fileName, githubRepositories);
    }

    private void saveRepositories(String fileName, List<GithubRepository> githubRepositories) throws IOException {
        File dir = new File("repositores");
        dir.mkdirs();

        File file = new File(dir, fileName);

        FileWriter writer = new FileWriter(file);

        writer.write(gson.toJson(githubRepositories));
        writer.flush();
        writer.close();
    }

    private void saveTotalLanguages(String fileName, Map<String, Long> languages) throws IOException {
        File dir = new File("languages");
        dir.mkdirs();

        File file = new File(dir, fileName);

        FileWriter writer = new FileWriter(file);

        writer.write(gson.toJson(languages));
        writer.flush();
        writer.close();
    }
}
