package sold.monkeytech.com.sold_android.framework.models;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.cache.PropertyCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 05/03/2018.
 */

public class Property extends BaseModel<Property> {

    //short model

    private String crmId;
    private String coverPhoto;
    private String houseNumber;
    private String apt;
    private int floor;
    private String lat;
    private String lng;
    private String roomsCount;
    private int bathroomCount;
    private int wcsCount;
    private int floorArea;
    private int plotArea;
    private String propertyStatus;
    private String createdAt;
    private Price price;
    private Price meterPrice;
    private Address address;
    private PropertyType propertyType;
    private List<Meeting> meetings;

    private boolean isFavorite;

    // Longer Model

    private String description;
    private String virtualTourLink;
    private String virtualTourCover;
    private int apartmentsCount;
    private int apartmentsPerFloor;
    private int floorsCount;
    private int parkingSlot;
    private String leads;
    private String direction;
    private int builtAt;
    private boolean isForeclosure;
    private String houseCommitteeTaxDetails;
    private List<String> album;
    private Price propertyTax;
    private Price houseCommitteeTax;
    private List<OpenHouse> openHouse;
    private List<TaxRecord> taxRecords;
    private List<PropertyFeatures> propertyFeatures;
    private List<POI> poi;

    private List<Property> nearbyProperties; //short property
    private int shareCount;
    private int viewCount;
    private int likesCount;


    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public double getDoubleLat(){
        if(!TextUtils.isEmpty(getLat()))
            return Double.parseDouble(getLat());
        return 0;
    }

    public double getDoubleLng(){
        if(!TextUtils.isEmpty(getLng()))
            return Double.parseDouble(getLng());
        return 0;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(String roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public int getWcsCount() {
        return wcsCount;
    }

    public void setWcsCount(int wcsCount) {
        this.wcsCount = wcsCount;
    }

    public int getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(int floorArea) {
        this.floorArea = floorArea;
    }

    public int getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(int plotArea) {
        this.plotArea = plotArea;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Price getMeterPrice() {
        return meterPrice;
    }

    public void setMeterPrice(Price meterPrice) {
        this.meterPrice = meterPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    //Long Model Method


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVirtualTourLink() {
        return virtualTourLink;
    }

    public void setVirtualTourLink(String virtualTourLink) {
        this.virtualTourLink = virtualTourLink;
    }

    public String getVirtualTourCover() {
        return virtualTourCover;
    }

    public void setVirtualTourCover(String virtualTourCover) {
        this.virtualTourCover = virtualTourCover;
    }

    public int getApartmentsCount() {
        return apartmentsCount;
    }

    public void setApartmentsCount(int apartmentsCount) {
        this.apartmentsCount = apartmentsCount;
    }

    public int getApartmentsPerFloor() {
        return apartmentsPerFloor;
    }

    public void setApartmentsPerFloor(int apartmentsPerFloor) {
        this.apartmentsPerFloor = apartmentsPerFloor;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public int getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(int parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getLeads() {
        return leads;
    }

    public void setLeads(String leads) {
        this.leads = leads;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getBuiltAt() {
        return builtAt;
    }

    public void setBuiltAt(int builtAt) {
        this.builtAt = builtAt;
    }

    public boolean isForeclosure() {
        return isForeclosure;
    }

    public void setForeclosure(boolean foreclosure) {
        isForeclosure = foreclosure;
    }

    public String getHouseCommitteeTaxDetails() {
        return houseCommitteeTaxDetails;
    }

    public void setHouseCommitteeTaxDetails(String houseCommitteeTaxDetails) {
        this.houseCommitteeTaxDetails = houseCommitteeTaxDetails;
    }

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
    }

    public Price getPropertyTax() {
        return propertyTax;
    }

    public void setPropertyTax(Price propertyTax) {
        this.propertyTax = propertyTax;
    }

    public Price getHouseCommitteeTax() {
        return houseCommitteeTax;
    }

    public void setHouseCommitteeTax(Price houseCommitteeTax) {
        this.houseCommitteeTax = houseCommitteeTax;
    }

    public List<OpenHouse> getOpenHouse() {
        return openHouse;
    }

    public void setOpenHouse(List<OpenHouse> openHouse) {
        this.openHouse = openHouse;
    }

    public List<PropertyFeatures> getPropertyFeatures() {
        return propertyFeatures;
    }

    public void setPropertyFeatures(List<PropertyFeatures> propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }

    public List<TaxRecord> getTaxRecords() {
        return taxRecords;
    }

    public void setTaxRecords(List<TaxRecord> taxRecords) {
        this.taxRecords = taxRecords;
    }

    public List<POI> getPoi() {
        return poi;
    }

    public void setPoi(List<POI> poi) {
        this.poi = poi;
    }

    public List<Property> getNearbyProperties() {
        return nearbyProperties;
    }

    public void setNearbyProperties(List<Property> nearbyProperties) {
        this.nearbyProperties = nearbyProperties;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return PropertyCache.getInstance();
    }

    @Override
    protected Class getType() {
        return Property.class;
    }

    public List<Meta> getHistorySales() {
        Meta m = new Meta("הירקון 21, תל אביב", "2,500,000");
        Meta m2 = new Meta("הירקון 91, תל אביב", "4,500,000");
        List<Meta> salesAround = new ArrayList<>();
        salesAround.add(m);
        salesAround.add(m2);
        return salesAround;
    }

    public String getFullAddress(){
        if(getAddress() != null && !TextUtils.isEmpty(getHouseNumber()))
            return getAddress().getStreetName() + " " + getHouseNumber();
        return "";
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
