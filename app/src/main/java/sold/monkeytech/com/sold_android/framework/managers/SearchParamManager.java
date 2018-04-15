package sold.monkeytech.com.sold_android.framework.managers;

import com.google.android.gms.maps.model.LatLng;
import com.pixplicity.easyprefs.library.Prefs;

import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;

/**
 * Created by MonkeyFather on 15/03/2018.
 */

public class SearchParamManager {

    private static final String SORT_ID = "sortId";
    private static final String SORT_DIRECTION = "sortDirection";

    public static SearchParamManager instance;
    ParamBuilder paramBuilder;

    private long sortId;
    private String sortDirection;
    private LatLng lastSearchLatLng;
    String typesCsv = "";
    String featuresCsv = "";
    Integer minFloorArea = null;
    Integer maxFloorArea = null;
    Integer minPlotArea = null;
    Integer maxPlotArea = null;
    Integer minPrice = null;
    Integer maxPrice = null;
    Integer minFloors = null;
    Integer maxFloors = null;
    Integer minRooms = null;
    Integer minBaths = null;
    Boolean hasOpenHouse = null;
    Boolean hideForeclosure = null;
    Boolean hasParking = null;
    Boolean hideNewConstruction = null;
    private Location lastSearchLocation;

    public static SearchParamManager getInstance() {
        if (instance == null) {
            instance = new SearchParamManager();
        }
        return instance;
    }

    public ParamBuilder getParams() {
        if (paramBuilder == null) {
            paramBuilder = new ParamBuilder();
            paramBuilder.addInt("location_id", 843); //default TelAviv Location
            paramBuilder.addText("location_type", "City");
        }
        paramBuilder.addInt("sortable_id", getSortId());
        paramBuilder.addText("sort_direction", getSortDirection());
        if(typesCsv != null)
            paramBuilder.addText("property_type_ids", typesCsv);
        if(featuresCsv != null)
            paramBuilder.addText("feature_ids", featuresCsv);
        if(minFloorArea != null)
            paramBuilder.addInt("min_floor_area", minFloorArea);
        if(maxFloorArea != null)
            paramBuilder.addInt("max_floor_area", maxFloorArea);
        if(minPlotArea != null)
            paramBuilder.addInt("min_plot_area", minPlotArea);
        if(maxPlotArea != null)
            paramBuilder.addInt("max_plot_area", maxPlotArea);
        if(minPrice != null)
            paramBuilder.addInt("min_price", minPrice);
        if(maxPrice != null)
            paramBuilder.addInt("max_price", maxPrice);
        if(minFloors != null)
            paramBuilder.addInt("min_floor", minFloors);
        if(maxFloors != null)
            paramBuilder.addInt("max_floor", maxFloors);
        if(minRooms != null)
            paramBuilder.addInt("min_rooms_count", minRooms);
        if(minBaths != null)
            paramBuilder.addInt("min_bathrooms_count", minBaths);
        if(hasOpenHouse != null)
            paramBuilder.addBoolean("has_open_house", hasOpenHouse);
        if(hideForeclosure != null)
            paramBuilder.addBoolean("hide_foreclosures", hideForeclosure);
        if(hasParking != null)
            paramBuilder.addBoolean("has_parking", hasParking);
        if(hideNewConstruction != null)
            paramBuilder.addBoolean("hide_new_construction", hideNewConstruction);

        return paramBuilder;
    }

    public void updateParams(String key, String value) {
        if (paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addText(key, value);
    }

    public void updateParams(String key, int value) {
        if (paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addInt(key, value);
    }

    public void updateParams(String key, long value) {
        if (paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addLong(key, value);
    }

    public void updateParams(String key, Double value) {
        if (paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addDouble(key, value);
    }

    public void updateParams(String key, boolean value) {
        if (paramBuilder == null)
            paramBuilder = new ParamBuilder();
        paramBuilder.addBoolean(key, value);
    }

    public void clearParams() {
        paramBuilder = null;
        typesCsv = "";
        featuresCsv = "";
        minFloorArea = null;
        maxFloorArea = null;
        minPlotArea = null;
        maxPlotArea = null;
        minPrice = null;
        maxPrice = null;
        minFloors = null;
        maxFloors = null;
        minRooms = null;
        minBaths = null;
        hasOpenHouse = null;
        hideForeclosure = null;
        hasParking = null;
        hideNewConstruction = null;
    }

    public void setSortId(long sortId) {
        this.sortId = sortId;
        Prefs.putInt(SORT_ID, (int) sortId);
    }

    public int getSortId() {
        return Prefs.getInt(SORT_ID, 0);
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
        Prefs.putString(SORT_DIRECTION, sortDirection);
    }

    public String getSortDirection() {
        return Prefs.getString(SORT_DIRECTION, "");
    }

    public void setLastSearchLatLng(LatLng lastSearchLatLng) {
        this.lastSearchLatLng = lastSearchLatLng;
    }

    public LatLng getLastSearchLatLng() {
        return lastSearchLatLng;
    }

    public String getTypesCsv() {
        return typesCsv;
    }

    public void setTypesCsv(String typesCsv) {
        this.typesCsv = typesCsv;
    }

    public String getFeaturesCsv() {
        return featuresCsv;
    }

    public void setFeaturesCsv(String featuresCsv) {
        this.featuresCsv = featuresCsv;
    }

    public int getMinFloorArea() {
        return minFloorArea;
    }

    public void setMinFloorArea(int minFloorArea) {
        this.minFloorArea = minFloorArea;
    }

    public int getMaxFloorArea() {
        return maxFloorArea;
    }

    public void setMaxFloorArea(int maxFloorArea) {
        this.maxFloorArea = maxFloorArea;
    }

    public int getMinPlotArea() {
        return minPlotArea;
    }

    public void setMinPlotArea(int minPlotArea) {
        this.minPlotArea = minPlotArea;
    }

    public int getMaxPlotArea() {
        return maxPlotArea;
    }

    public void setMaxPlotArea(int maxPlotArea) {
        this.maxPlotArea = maxPlotArea;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinFloors() {
        return minFloors;
    }

    public void setMinFloors(int minFloors) {
        this.minFloors = minFloors;
    }

    public int getMaxFloors() {
        return maxFloors;
    }

    public void setMaxFloors(int maxFloors) {
        this.maxFloors = maxFloors;
    }

    public int getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(int minRooms) {
        this.minRooms = minRooms;
    }

    public int getMinBaths() {
        return minBaths;
    }

    public void setMinBaths(int minBaths) {
        this.minBaths = minBaths;
    }

    public boolean isHasOpenHouse() {
        return hasOpenHouse;
    }

    public void setHasOpenHouse(boolean hasOpenHouse) {
        this.hasOpenHouse = hasOpenHouse;
    }

    public boolean isHideForeclosure() {
        return hideForeclosure;
    }

    public void setHideForeclosure(boolean hideForeclosure) {
        this.hideForeclosure = hideForeclosure;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHideNewConstruction() {
        return hideNewConstruction;
    }

    public void setHideNewConstruction(boolean hideNewConstruction) {
        this.hideNewConstruction = hideNewConstruction;
    }

    public void setLastSearchLocation(Location lastSearchLocation) {
        this.lastSearchLocation = lastSearchLocation;
    }

    public Location getLastSearchLocation() {
        //return last search or default search if last is null
        if(lastSearchLatLng != null)
            return lastSearchLocation;
        else
            return getDefaultLocation();
    }

    private Location getDefaultLocation() {
        return MetaDataManager.getInstance().getDefaultLocation();
    }
}
