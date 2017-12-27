package in.dragonbra.dragonbrain.controller.dto;

/**
 * @author lngtr
 * @since 2017-12-27
 */
public class UserDto {

    private String username;

    private String password;

    private Boolean root = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }
}
