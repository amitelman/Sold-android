package sold.monkeytech.com.sold_android.framework.models;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.cache.SavedSearchCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class SavedSearch extends BaseModel<SavedSearch> {

    private String name;
    private String lat;
    private String lng;
    private List<PropertyType> propertyType;
    private List<PropertyFeatures> features;
    private int minFloorArea;
    private int maxFloorArea;
    private int minPlotArea;
    private int maxPlotArea;
    private int minFloors;
    private int maxFloors;
    private Price minPrice;
    private Price maxPrice;
    private int minRooms;
    private int minBaths;
    private boolean hasOpenHouse;
    private boolean hasParking;
    private boolean hideForeclosures;
    private boolean hideNewConstruction;
    private String createdAt;

    public SavedSearch(){
    }

    public String getFilterCount(){
        int counter = 0;
        if(getPropertyType().size() > 0)
            counter++;
        if(features.size() > 0)
            counter++;
        if(minFloorArea != 0 || maxFloorArea != 0)
            counter++;
        if(minPlotArea != 0 || maxPlotArea != 0)
            counter++;
        if(minRooms != 0)
            counter++;
        if(minBaths != 0)
            counter++;
        if(minPrice != null || maxPrice != null)
            counter++;
        if(hasOpenHouse)
            counter++;
        if(hasParking)
            counter++;
        if(hideForeclosures)
            counter++;
        if(hideNewConstruction)
            counter++;

        return "(+" + counter + " filters)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PropertyType> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(List<PropertyType> propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeCsv(){
        String csv = "";
        if(getPropertyType().size() > 0){
            for(PropertyType pt : getPropertyType())
                csv += pt.getId() + ",";
        }
        return csv;
    }

    public List<PropertyFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(List<PropertyFeatures> features) {
        this.features = features;
    }

    public String getFeaturesCsv(){
        String csv = "";
        if(getFeatures().size() > 0){
            for(PropertyFeatures pf : getFeatures())
                csv += pf.getId() + ",";
        }
        return csv;
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

    public Price getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Price minPrice) {
        this.minPrice = minPrice;
    }

    public Price getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Price maxPrice) {
        this.maxPrice = maxPrice;
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

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHideForeclosures() {
        return hideForeclosures;
    }

    public void setHideForeclosures(boolean hideForeclosures) {
        this.hideForeclosures = hideForeclosures;
    }

    public boolean isHideNewConstruction() {
        return hideNewConstruction;
    }

    public void setHideNewConstruction(boolean hideNewConstruction) {
        this.hideNewConstruction = hideNewConstruction;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return SavedSearchCache.getInstance();
    }

    @Override
    protected Class getType() {
        return SavedSearch.class;
    }
}
