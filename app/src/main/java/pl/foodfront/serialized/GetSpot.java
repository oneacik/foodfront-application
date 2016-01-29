package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-29.
 */
public class GetSpot implements iSend {

    private String function;

    private Long id;

    public GetSpot() {
        this.function = "getSpot";
    }

    @Override
    public String getFunction() { return function; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

}
