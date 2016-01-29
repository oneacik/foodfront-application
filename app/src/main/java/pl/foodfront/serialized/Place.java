package pl.foodfront.serialized;

/**
 * Created by Michał Stobiński on 2016-01-28.
 */
public class Place {

    private Integer menu;
    private String title;
    private Double lat;
    private Double lng;
    private Integer map_icon;
    private Integer hi_icon;
    private Long id;

    public Integer getMenu() { return menu; }

    public void setMenu(Integer menu) { this.menu = menu; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Double getLat() { return lat; }

    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }

    public void setLng(Double lng) { this.lng = lng; }

    public Integer getMap_icon() { return map_icon; }

    public void setMap_icon(Integer map_icon) { this.map_icon = map_icon; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getHi_icon() { return hi_icon; }

    public void setHi_icon(Integer hi_icon) { this.hi_icon = hi_icon; }

}
