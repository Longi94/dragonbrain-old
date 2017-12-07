package in.dragonbra.dragonbrain;

import in.dragonbra.dragonbrain.model.ScheduledTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "in.dragonbra.dragonbrain.DragonBrainApplication"))
public class ScheduledTaskRunner {
    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskRunner.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Available scheduled tasks, invoke a task by typing its number into the console:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            List<ScheduledTask> tasks = new ArrayList<>();

            int i = 0;

            for (String beanName : beanNames) {
                Object bean = ctx.getBean(beanName);

                for (Method method : bean.getClass().getMethods()) {
                    if (method.isAnnotationPresent(Scheduled.class)) {
                        System.out.println(i++ + " " + beanName + " -> " + method.getName());
                        tasks.add(new ScheduledTask(bean, method));
                    }
                }
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while (!(line = br.readLine()).equals("q")) {
                try {
                    int index = Integer.parseInt(line);

                    if (tasks.size() > index) {
                        tasks.get(index).run();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("not a number");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            SpringApplication.exit(ctx);
        };
    }
}
