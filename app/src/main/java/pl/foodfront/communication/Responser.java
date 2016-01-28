package pl.foodfront.communication;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import pl.foodfront.ICallback;
import pl.foodfront.wrappers.Spots;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
class Responser {

    private static final String FUNCTION = "function";

    private static final String LOGIN = "login"; // funkcja logowania
    private static final String GET_SPOTS = "getSpots"; // funkcja pobierania listy lokali
    private static final String ERRNO = "errno"; // odpowiedź na żądanie
    private static final String ERROR = "error"; // odpowiedź na żądanie - komunikat

    private ICallback callback;

    Responser(ICallback callback) {
        this.callback = callback;
    }

    protected void answerMe(Map<String, String> map, String response) {
        switch (map.get(FUNCTION)) {
            case LOGIN:
                answerForLogin(response);
                break;
            case GET_SPOTS:
                answerForGetSpots(response);
                break;
            default:
                break;
        }
    }

    private void answerForLogin(String response) {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> mapResponse = new GsonBuilder().create().fromJson(response, type);

        switch(mapResponse.get(ERRNO)) {
            case "0":
                callback.loginInfo(true, mapResponse.get(ERROR));
                break;
            default:
                callback.loginInfo(false, mapResponse.get(ERROR));
                break;
        }
    }

    private void answerForGetSpots(String response) {
        Spots spots = new GsonBuilder().create().fromJson(response, Spots.class);

        switch(spots.getErrno()) {
            case "0":
                callback.invokeSpots(spots);
                break;
            default:
                break;
        }

    }

}
