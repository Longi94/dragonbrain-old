package in.dragonbra.dragonbrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lngtr
 * @since 7/16/2017
 */
@SpringBootApplication
@EnableScheduling
public class DragonBrainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DragonBrainApplication.class, args);
    }
}
