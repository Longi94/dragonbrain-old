package in.dragonbra.dragonbrain.scheduled;

import com.google.gson.Gson;
import in.dragonbra.dragonbrain.service.GithubService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Component
public class ScheduledTasks {

    private static final Logger logger = LogManager.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final GithubService githubService;

    private final Gson gson = new Gson();

    @Autowired
    public ScheduledTasks(GithubService githubService) {
        this.githubService = githubService;
    }

    @Scheduled(cron = "0 0 22 * * *")
    public void updateLanguages() throws IOException {
        logger.info("running scheduled task updateLanguages");
        Map<String, Long> languages = githubService.getLanguages();

        String date = FORMAT.format(new Date());

        File dir = new File("languages");
        dir.mkdirs();

        File file = new File(dir, date + ".json");

        FileWriter writer = new FileWriter(file);

        writer.write(gson.toJson(languages));
        writer.flush();
        writer.close();

        logger.info("finished scheduled task updateLanguages");
    }
}
