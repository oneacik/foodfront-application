package pl.foodfront.communication;

import java.util.Map;

import pl.foodfront.ICallback;
import pl.foodfront.serialized.Login;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
public class Bridge {

    /*
        TODO - uzupełnić URL
        e.g.: http://HOST:PORT/nazwa.php
        http://192.168.1.3:1337/android.php
     */
    private static final String URL = "http://192.168.1.3:1337/android.php";

    private HttpCommunicationService httpService;

    public Bridge() {
        httpService = new HttpCommunicationService();
    }

    public void connectActivity(ICallback callback) {
        httpService.connectActivity(callback);
    }

    public void disconnectActivity() {
        httpService.disconnectActivity();
    }

    public void login(String user, String password) {
        Login login = new Login();
        login.setUsername(user);
        login.setPassword(password);

        httpService.invoke(URL, login);
    }

}
