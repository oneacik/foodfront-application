package pl.foodfront.communication;

import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import pl.foodfront.ICallback;
import pl.foodfront.wrappers.Place;
import pl.foodfront.wrappers.Spots;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Michał Stobiński on 2016-01-25.
 */

@RunWith(MockitoJUnitRunner.class)
public class ResponserTest {

    @Mock private ICallback callback;
    private Responser responser;

    @Before
    public void initialize() {
        responser = new Responser(callback);
    }

    /*
        Weryfikacja czy poprawna metoda jest wywoływana w zależności od odebranego JSONa
     */
    @Test
    public void shouldInvokeLoginMethod() {

        // given
        Map<String, String> inputJson = getLoginPost();
        String responseJson = getLoginResponse();

        // when
        responser.answerMe(inputJson, responseJson);

        // then
        verify(callback, times(1)).loginInfo(true, "");
    }

    @Test
    public void shouldInkoveGetSpotsMethod() {

        // given
        Map<String, String> inputJson = getGetSpotsPost();
        String responseJson = getGetSpotsResponse();

        // when
        responser.answerMe(inputJson, responseJson);

        // then
        verify(callback, times(1)).invokeSpots(any(Spots.class));
    }

    private Map<String, String> getLoginPost() {
        return new HashMap<String, String>() {{
                put("function", "login");
                put("username", "Kajtek");
                put("password", "gonzalo123@#wr");
            }};
    }

    private String getLoginResponse() {
        return new GsonBuilder().create().toJson(
                new HashMap<String, String>() {{
                    put("errno", "0");
                    put("error", "");
                }}, Map.class
        );
    }

    private Map<String, String> getGetSpotsPost() {
        return new HashMap<String, String>(){{ put("function", "getSpots"); }};
    }

    private String getGetSpotsResponse() {

        Place classic = new Place();
        classic.setTitle("Classic burger");
        classic.setLat("1");
        classic.setLng("1");
        classic.setMap_icon("12");

        Place mcDonald = new Place();
        mcDonald.setTitle("McDonald");
        mcDonald.setLat("2");
        mcDonald.setLng("2");
        mcDonald.setMap_icon("11");

        Place[] places = new Place[] {classic, mcDonald};

        Spots spots = new Spots();
        spots.setPlaces(places);
        spots.setError("");
        spots.setErrno("0");

        return new GsonBuilder().create().toJson(spots, Spots.class);

    }

}