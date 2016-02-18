package pl.foodfront.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pl.foodfront.serialized.Place;
import pl.foodfront.serialized.Spots;

/**
 * Created by Michał Stobiński on 2016-02-18.
 */
public class MapHelper {

    private GoogleMap googleMap;

    public MapHelper(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void disposeSpotsOnMap(Spots spots) {
        for(Place place : spots.getPlaces()) {
            disposePlaceOnMap(place);
        }
    }

    public void disposePlaceOnMap(Place place) {
        LatLng latLng = new LatLng(place.getLat(), place.getLng());
        MarkerOptions marker = new MarkerOptions();
        marker.position(latLng).title(place.getTitle());
        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
    }

}
