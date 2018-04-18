package sold.monkeytech.com.sold_android.framework.models;

import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class Location extends BaseModel<Location> {

    private String locationName;
    private String locationType;
    private double lat;
    private double lng;

    public Location(){
    }

    public Location(long id, String name, String type) {
        setId(id);
        setLocationName(name);
        setLocationType(type);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
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

    @Override
    public BaseCache getInstanceOfCache() {
        return null;
    }

    @Override
    protected Class getType() {
        return Location.class;
    }
}
