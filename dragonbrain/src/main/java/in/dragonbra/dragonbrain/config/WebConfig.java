package in.dragonbra.dragonbrain.config;

import in.dragonbra.dragonbrain.interceptor.ExecuteTimeInterceptor;
import in.dragonbra.dragonbrain.interceptor.UserInterceptor;
import in.dragonbra.dragonbrain.util.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${env}")
    private String environment;

    private final UserInterceptor userInterceptor;

    @Autowired
    public WebConfig(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExecuteTimeInterceptor());
        registry.addInterceptor(userInterceptor);
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (!Environment.PRODUCTION.equals(environment)) {
            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
        }
    }
}