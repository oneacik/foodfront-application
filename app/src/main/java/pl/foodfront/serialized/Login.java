package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Login implements iSend {

    private String function;
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.function = "login";
    }

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

    @Override  public String getFunction() { return function; }
}
