package pl.foodfront.serialized;

import com.google.gson.GsonBuilder;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class GetSpots implements iSerialize {

    private String function;

    public GetSpots() {
        function = "getSpots";
    }

    @Override public String getFunction() { return function; }

    @Override
    public String serializeToJson() {
        return new GsonBuilder().create().toJson(this, GetSpots.class);
    }

}
