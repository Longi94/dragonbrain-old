package in.dragonbra.dragonbrain.model;

import java.util.Map;

/**
 * @author lngtr
 * @since 2018-02-12
 */
public class GithubRepository {
    private Map<String, Long> languages;

    private long id;

    private long updatedAt;

    public GithubRepository(Map<String, Long> languages, long id, long updatedAt) {
        this.languages = languages;
        this.id = id;
        this.updatedAt = updatedAt;
    }

    public Map<String, Long> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, Long> languages) {
        this.languages = languages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
