package in.dragonbra.dragonbrain.config;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lngtr
 * @since 2018-02-01
 */
@Configuration
public class GithubConfig {

    @Value("${github.access-token}")
    private String githubAuthToken;

    @Bean
    public GitHubClient gitHubClient() {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(githubAuthToken);
        return client;
    }

    @Bean
    public RepositoryService repositoryService(GitHubClient client) {
        return new RepositoryService(client);
    }
}
