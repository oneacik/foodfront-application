package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class GetSpots implements iSend {

    private String function;

    public GetSpots() {
        function = "getSpots";
    }

    @Override public String getFunction() { return function; }
}
