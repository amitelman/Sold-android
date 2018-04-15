package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class PropertyParser extends GeneralParser<Property> {

    public static int TYPE_FULL = 0;
    public static int TYPE_SHORT = 1;
    private int type;

    public PropertyParser(int type) {
        super();
        this.type = type;
    }


    @Override
    public Property parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Property property = null;

        property = getObjectFromCache(jo,"id");
        if(property == null){
            property = new Property();
            property.setId(safeParseLong(jo, "id"));
        }

        property.setCrmId(safeParseString(jo, "crm_id"));
        property.setCoverPhoto(safeParseString(jo, "cover_photo"));
        property.setHouseNumber(safeParseString(jo, "house_number"));
        property.setApt(safeParseString(jo, "apt"));
        property.setFloor(safeParseInt(jo, "floor"));
        property.setLat(safeParseString(jo, "lat"));
        property.setLng(safeParseString(jo, "lng"));
        property.setRoomsCount(safeParseString(jo, "rooms_count"));
        property.setBathroomCount(safeParseInt(jo, "bathrooms_count"));
        property.setWcsCount(safeParseInt(jo, "wcs_count"));
        property.setFloorArea(safeParseInt(jo, "floor_area"));
        property.setPlotArea(safeParseInt(jo, "plot_area"));
        property.setPropertyStatus(safeParseString(jo, "property_status"));
        property.setCreatedAt(safeParseString(jo, "created_at"));
        property.setPrice(new PriceParser().parseToSingle(jo.getJSONObject("price")));
        property.setMeterPrice(new PriceParser().parseToSingle(jo.getJSONObject("meter_price")));
        property.setAddress(new AddressParser(AddressParser.STREET).parseToSingle(jo.getJSONObject("street")));
        property.setPropertyType(new PropertyTypeParser().parseToSingle(jo.getJSONObject("property_type")));

        if(type == TYPE_FULL){
            property.setDescription(safeParseString(jo, "description"));
            property.setVirtualTourLink(safeParseString(jo, "virtual_tour_link"));
            property.setApartmentsCount(safeParseInt(jo, "apartments_count"));
            property.setApartmentsPerFloor(safeParseInt(jo, "apartments_per_floor"));
            property.setFloorsCount(safeParseInt(jo, "floors_count"));
            property.setParkingSlot(safeParseInt(jo, "parking_slots"));
            property.setLeads(safeParseString(jo, "leads"));
            property.setDirection(safeParseString(jo, "direction"));
            property.setBuiltAt(safeParseInt(jo, "built_at"));
            property.setForeclosure(safeParseBoolean(jo, "is_foreclosure"));
            property.setHouseCommitteeTaxDetails(safeParseString(jo, "house_committee_tax_details"));

            JSONArray album = jo.getJSONArray("album");
            List<String> albumArr = new ArrayList<>();
            for (int i = 0; i < album.length(); i++) {
                String link = album.getString(i);
                albumArr.add(link);
            }
            property.setAlbum(albumArr);

            if(!jo.isNull("property_tax"))
                property.setPropertyTax(new PriceParser().parseToSingle(jo.getJSONObject("property_tax")));
            if(!jo.isNull("house_committee_tax"))
                property.setHouseCommitteeTax(new PriceParser().parseToSingle(jo.getJSONObject("house_committee_tax")));

            if(!jo.isNull("open_house"))
                property.setOpenHouse(new OpenHouseParser().parseToList(jo.getJSONArray("open_house")));

            if(!jo.isNull("property_features"))
                property.setPropertyFeatures(new PropertyFeaturesParser().parseToList(jo.getJSONArray("property_features")));
            if(!jo.isNull("tax_records"))
                property.setTaxRecords(new TaxRecordParser().parseToList(jo.getJSONArray("tax_records")));
            if(!jo.isNull("pois"))
                property.setPoi(new POIParser().parseToList(jo.getJSONArray("pois")));
            if(!jo.isNull("nearby_properties"))
                property.setNearbyProperties(new PropertyParser(TYPE_SHORT).parseToList(jo.getJSONArray("nearby_properties")));
        }

        return property;
    }

    @Override
    protected Class getType() {
        return Property.class;
    }
}

