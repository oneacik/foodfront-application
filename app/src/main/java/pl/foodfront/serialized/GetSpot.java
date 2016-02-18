package pl.foodfront.serialized;

import com.google.gson.GsonBuilder;

/**
 * Created by Michał Stobiński on 2016-01-29.
 */
public class GetSpot implements iSerialize {

    private String function;

    private Long id;

    public GetSpot(Long id) {
        this.function = "getSpot";
        this.id = id;
    }

    @Override
    public String getFunction() { return function; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String serializeToJson() {
        return new GsonBuilder().create().toJson(this, GetSpot.class);
    }

}
