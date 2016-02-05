package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import pl.foodfront.serialized.Error;
import pl.foodfront.serialized.Place;
import pl.foodfront.serialized.Spots;
import pl.foodfront.serialized.iSend;
import pl.foodfront.views.ICallback;
import pl.foodfront.views.ILoginCallback;
import pl.foodfront.views.IMainCallback;

/**
 * Created by Michał Stobiński on 2016-01-24.
 */
class Responser {

    private ICallback callback;

    Responser(ICallback callback) {
        this.callback = callback;
    }

    protected void answerMe(iSend send, String response) {

        String s = send.getFunction();
        if (s.equals(eFunctions.LOGIN.toString())) {
            answerForLogin(response);

        } else if (s.equals(eFunctions.GET_SPOTS.toString())) {
            answerForGetSpots(response);

        } else if (s.equals(eFunctions.GET_SPOT.toString())) {
            answerForGetSpot(response);

        }

    }

    private void answerForLogin(String response) {
        Error error = new GsonBuilder().create().fromJson(response, Error.class);

        switch(error.getErrno()) {
            case 0:
                ((ILoginCallback) callback).loginInfo(true, error.getError());
                break;
            default:
                ((ILoginCallback) callback).loginInfo(false, error.getError());
                break;
        }
    }

    private void answerForGetSpots(String response) {
        Spots spots = new GsonBuilder().create().fromJson(response, Spots.class);

        switch(spots.getError().getErrno()) {
            case 0:
                ((IMainCallback) callback).invokeSpots(spots);
                break;
            default:
                ((IMainCallback) callback).invokeSpots(null);
                break;
        }

    }

    private void answerForGetSpot(String response) {
        Place place = new GsonBuilder().create().fromJson(response, Place.class);

        switch(place.getError().getErrno()) {
            case 0:
                // TODO jakoś zareagować na odpowiedź
                break;
            default:
                break;
        }

    }

}
