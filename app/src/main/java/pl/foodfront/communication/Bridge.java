package pl.foodfront.communication;

import pl.foodfront.serialized.GetSpot;
import pl.foodfront.serialized.GetSpots;
import pl.foodfront.serialized.Login;
import pl.foodfront.views.ICallback;

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

    public void login(String user, String password) { httpService.invoke(URL, new Login(user, password)); }
    public void getSpots() { httpService.invoke(URL, new GetSpots()); }
    public void getSpot(Long id) { httpService.invoke(URL, new GetSpot(id)); }

}
