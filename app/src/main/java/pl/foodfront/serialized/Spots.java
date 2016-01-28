package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Spots {

    private Place[] places;

    private Error error;

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
