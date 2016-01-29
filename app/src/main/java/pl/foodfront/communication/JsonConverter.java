package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import pl.foodfront.serialized.GetSpots;
import pl.foodfront.serialized.Login;
import pl.foodfront.serialized.iSend;

/**
 * Created by Michał Stobiński on 2016-01-29.
 */
class JsonConverter {

    private static final String LOGIN = "login"; // funkcja logowania
    private static final String GET_SPOTS = "getSpots"; // funkcja pobierania listy lokali

    protected static String serializePost(iSend send) {

        switch(send.getFunction()) {
            case LOGIN:
                return new GsonBuilder().create().toJson(send, Login.class);
            case GET_SPOTS:
                return new GsonBuilder().create().toJson(send, GetSpots.class);
            default:
                return null;
        }

    }

}
