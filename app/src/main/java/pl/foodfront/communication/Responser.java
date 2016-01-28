package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import pl.foodfront.ICallback;
import pl.foodfront.serialized.Error;
import pl.foodfront.serialized.Spots;
import pl.foodfront.serialized.iSend;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
class Responser {

    private static final String LOGIN = "login"; // funkcja logowania
    private static final String GET_SPOTS = "getSpots"; // funkcja pobierania listy lokali

    private ICallback callback;

    Responser(ICallback callback) {
        this.callback = callback;
    }

    protected void answerMe(iSend send, String response) {
        switch (send.getFunction()) {
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
        Error error = new GsonBuilder().create().fromJson(response, Error.class);

        switch(error.getErrno()) {
            case 0:
                callback.loginInfo(true, error.getError());
                break;
            default:
                callback.loginInfo(false, error.getError());
                break;
        }
    }

    private void answerForGetSpots(String response) {
        Spots spots = new GsonBuilder().create().fromJson(response, Spots.class);

        switch(spots.getError().getErrno()) {
            case 0:
                callback.invokeSpots(spots);
                break;
            default:
                break;
        }

    }

}
