package pl.foodfront.wrappers;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Spots {

    private Place[] places;
    private String error;
    private String errno;

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }
}
