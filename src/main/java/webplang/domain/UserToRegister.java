package webplang.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Michał on 2017-07-26.
 * Class used for registration process
 */
public class UserToRegister {

    @NotNull
    @Size(min = 5, max = 45)
    @Pattern(regexp = "([a-z])+")
    private String login;

    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")
    private String password;

    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")
    private String repeatedPassword;


    public UserToRegister() {
    }

    public UserToRegister(String login, String password, String repeatedPassword) {
        this.login = login;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
