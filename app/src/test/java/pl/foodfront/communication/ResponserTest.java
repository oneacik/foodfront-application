package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import pl.foodfront.serialized.Error;
import pl.foodfront.serialized.GetSpots;
import pl.foodfront.serialized.Login;
import pl.foodfront.serialized.Place;
import pl.foodfront.serialized.Spots;
import pl.foodfront.serialized.iSend;
import pl.foodfront.views.ILoginCallback;
import pl.foodfront.views.IMainCallback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Michał Stobiński on 2016-01-25.
 */

@RunWith(MockitoJUnitRunner.class)
public class ResponserTest {

    private Responser responser;

    /*
        Weryfikacja czy poprawna metoda jest wywoływana w zależności od odebranego JSONa
     */
    @Test
    public void shouldInvokeLoginMethod() {

        // given
        ILoginCallback loginCallback = mock(ILoginCallback.class);

        responser = new Responser(loginCallback);
        iSend login = getLoginPost();
        String responseJson = getLoginResponse();

        // when

        responser.answerMe(login, responseJson);

        // then
        verify(loginCallback, times(1)).loginInfo(true, "");
    }

    @Test
    public void shouldInkoveGetSpotsMethod() {

        // given
        IMainCallback mainCallback = mock(IMainCallback.class);

        responser = new Responser(mainCallback);
        iSend getSpots = getGetSpotsPost();
        String responseJson = getGetSpotsResponse();

        // when
        responser.answerMe(getSpots, responseJson);

        // then
        verify(mainCallback, times(1)).invokeSpots(any(Spots.class));
    }

    private iSend getLoginPost() {
        return new Login("Kajtek", "gonzalo1232"); }

    private String getLoginResponse() {
        Error error = new Error();
        error.setError("");
        error.setErrno(0);
        return new GsonBuilder().create().toJson(error, Error.class);
    }

    private iSend getGetSpotsPost() {
        return new GetSpots(); }

    private String getGetSpotsResponse() {

        Place classic = new Place();
        classic.setTitle("Classic burger");
        classic.setLat(1d);
        classic.setLng(1d);
        classic.setId(1l);
        classic.setMap_icon(12);

        Place mcDonald = new Place();
        mcDonald.setTitle("McDonald");
        mcDonald.setLat(2d);
        mcDonald.setLng(2d);
        mcDonald.setId(2l);
        mcDonald.setMap_icon(11);

        Place[] places = new Place[] {classic, mcDonald};

        Spots spots = new Spots();
        Error error = new Error();

        spots.setPlaces(places);
        error.setError("");
        error.setErrno(0);
        spots.setError(error);

        return new GsonBuilder().create().toJson(spots, Spots.class);

    }

}