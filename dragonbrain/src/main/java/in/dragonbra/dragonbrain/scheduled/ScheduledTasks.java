package in.dragonbra.dragonbrain.scheduled;

import in.dragonbra.dragonbrain.service.GithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final GithubService githubService;

    @Autowired
    public ScheduledTasks(GithubService githubService) {
        this.githubService = githubService;
    }

    @Scheduled(cron = "0 0 22 * * *")
    public void updateLanguages() throws IOException {
        logger.info("running scheduled task updateLanguages");
        githubService.getLanguages();
        logger.info("finished scheduled task updateLanguages");
    }
}
