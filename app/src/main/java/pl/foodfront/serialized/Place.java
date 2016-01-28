package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Place {

    private String title;
    private double lat;
    private double lng;
    private int map_icon;

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getMap_icon() {
        return map_icon;
    }

    public void setMap_icon(int map_icon) {
        this.map_icon = map_icon;
    }

}
