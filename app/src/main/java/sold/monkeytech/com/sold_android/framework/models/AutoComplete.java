package sold.monkeytech.com.sold_android.framework.models;

/**
 * Created by MonkeyFather on 15/03/2018.
 */

public class AutoComplete {

    private CharSequence name;
    private String placeId;
    private double lat;
    private double lng;

    public AutoComplete(CharSequence address, String placeId) {
        setName(name);
        setPlaceId(placeId);
    }

    public AutoComplete() {
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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
}
