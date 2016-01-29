package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import pl.foodfront.serialized.GetSpots;
import pl.foodfront.serialized.Login;
import pl.foodfront.serialized.iSend;

/**
 * Created by Michał Stobiński on 2016-01-29.
 */
class JsonConverter {

    protected static String serializePost(iSend send) {

        String s = send.getFunction();

        if (s.equals(eFunctions.LOGIN.toString())) {
            return new GsonBuilder().create().toJson(send, Login.class);
        } else if (s.equals(eFunctions.GET_SPOTS.toString())) {
            return new GsonBuilder().create().toJson(send, GetSpots.class);
        } else if (s.equals(eFunctions.GET_SPOT.toString())) {
            return new GsonBuilder().create().toJson(send, GetSpots.class);
        } else {
            return null;
        }

    }

}
