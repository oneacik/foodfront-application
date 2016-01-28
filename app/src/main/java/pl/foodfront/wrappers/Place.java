package pl.foodfront.wrappers;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Place {

    private String title;
    private String lat;
    private String lng;
    private String map_icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMap_icon() {
        return map_icon;
    }

    public void setMap_icon(String map_icon) {
        this.map_icon = map_icon;
    }
}
