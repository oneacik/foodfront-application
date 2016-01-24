package pl.foodfront.communication;

import java.util.HashMap;
import java.util.Map;

import pl.foodfront.ICallback;

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

    private Map<String, String> mJson;
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

    public boolean login(String user, String password) {
        mJson = new HashMap<>();
        mJson.put("function", "login");
        mJson.put("username", user);
        mJson.put("password", password);

        httpService.invoke(URL, mJson);

        return true;
    }

}
