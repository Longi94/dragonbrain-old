package in.dragonbra.dragonbrain.scheduled;

import in.dragonbra.dragonbrain.service.CodeLanguageService;
import in.dragonbra.dragonbrain.service.GithubService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LogManager.getLogger(ScheduledTasks.class);

    private final GithubService githubService;

    private final CodeLanguageService codeLanguageService;

    @Autowired
    public ScheduledTasks(GithubService githubService, CodeLanguageService codeLanguageService) {
        this.githubService = githubService;
        this.codeLanguageService = codeLanguageService;
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void updateLanguages() throws IOException {
        logger.info("running scheduled task updateLanguages");
        Map<String, Long> languages = githubService.getLanguages();
        codeLanguageService.updateLanguages(languages);
        logger.info("finished scheduled task updateLanguages");
    }
}
