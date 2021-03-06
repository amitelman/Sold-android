package sold.monkeytech.com.sold_android.framework.managers;

import android.content.Context;

import com.monkeytechy.framework.interfaces.TAction;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;
import sold.monkeytech.com.sold_android.framework.models.TaxBracket;
import sold.monkeytech.com.sold_android.framework.parsers.IdLabelParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyFeaturesParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyTypeParser;
import sold.monkeytech.com.sold_android.framework.parsers.ServicePageParser;
import sold.monkeytech.com.sold_android.framework.parsers.TaxBracketParser;
import sold.monkeytech.com.sold_android.framework.parsers.TaxRecordParser;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;


/**
 * Created by MonkeyFather on 20/08/2017.
 */

public class MetaDataManager {

    public static final String MEETUP_DURATION = "meetup_duration";
    public static final String MEETUP_WEEKS = "datetimepicker_display_interval";
    public static final String MEETUP_START_AT = "datetimepicker_starts_at";
    public static final String MEETUP_END_AT = "datetimepicker_ends_at";
    public static MetaDataManager instance;
    private JSONObject metaData;


    public static MetaDataManager getInstance(){
        if(instance == null)
            instance = new MetaDataManager();
        return instance;
    }


    public void setMetaData(JSONObject metaData) {
        this.metaData = metaData;
    }

    public JSONObject getMetaData() {
        return metaData;
    }

    public List<PropertyType> getPropertyTypes(){
        List<PropertyType> propertyTypes = new ArrayList<>();
        if(metaData != null && getMetaData().length() != 0){
            try {
                propertyTypes = new PropertyTypeParser().parseToList(getMetaData().getJSONArray("property_types"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return propertyTypes;
    }

    public List<IdLabel> getPropertyFeatures(){
        List<IdLabel> propertyFeatures = new ArrayList<>();
        if(metaData != null && getMetaData().length() != 0){
            try {
                propertyFeatures = new IdLabelParser().parseToList(getMetaData().getJSONArray("features"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return propertyFeatures;
    }

    public List<ServicePage> getServicePages(){
        List<ServicePage> servicePages = new ArrayList<>();
        if(metaData != null && getMetaData().length() != 0){
            try {
                servicePages = new ServicePageParser().parseToList(getMetaData().getJSONArray("service_tutorial"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return servicePages;
    }

    public List<IdLabel> getSortables(){
        List<IdLabel> sortables = new ArrayList<>();
        if(metaData != null && getMetaData().length() != 0){
            try {
                sortables = new IdLabelParser().parseToList(getMetaData().getJSONArray("sortables"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sortables;
    }

    public JSONArray getSettings(){
        JSONArray settings = null;
        if(metaData != null && getMetaData().length() != 0){
            try {
                settings = getMetaData().getJSONArray("settings");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return settings;
    }


    public Location getDefaultLocation(){
        Location location = new Location();
        if(getSettings() != null){
            for (int i = 0; i < getSettings().length(); i++) {
                JSONObject row = null;
                try {
                    row = getSettings().getJSONObject(i);
                    if(row.get("key").equals("default_search_location_id"))
                        location.setId(row.getLong("value"));
                    if(row.get("key").equals("default_search_location_type"))
                        location.setLocationType((String) row.get("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return location;
    }

    public List<TaxBracket> getTaxBrackets(){
        List<TaxBracket> taxBrackets = new ArrayList<>();
        if(metaData != null && getMetaData().length() != 0){
            try {
                taxBrackets = new TaxBracketParser().parseToList(getMetaData().getJSONArray("tax_brackets"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return taxBrackets;
    }

    public List<TaxBracket> getSinglePropTax(){
        List<TaxBracket> singleTax = new ArrayList<>();
        if(getTaxBrackets().size() > 0){
            for(TaxBracket tb : getTaxBrackets()){
                if(tb.getPropertiesCount() == 1)
                    singleTax.add(tb);
            }
        }
        return singleTax;
    }

    public List<TaxBracket> getMultiPropTax(){
        List<TaxBracket> multiTax = new ArrayList<>();
        if(getTaxBrackets().size() > 0){
            for(TaxBracket tb : getTaxBrackets()){
                if(tb.getPropertiesCount() != 1)
                    multiTax.add(tb);
            }
        }
        return multiTax;
    }




    public String getStringValueFromString(String key){
        String value = "";
        if(getSettings() != null){
            for (int i = 0; i < getSettings().length(); i++) {
                JSONObject row = null;
                try {
                    row = getSettings().getJSONObject(i);
                    if(row.get("key").equals(key))
                        return row.getString("value");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public int getIntValueFromString(String key){
        int value = 0;
        if(getSettings() != null){
            for (int i = 0; i < getSettings().length(); i++) {
                JSONObject row = null;
                try {
                    row = getSettings().getJSONObject(i);
                    if(row.get("key").equals(key))
                        return row.getInt("value");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public List<Meta> getMetaDataMap(){
        List<Meta> meta = new ArrayList<>();
        meta.add(new Meta("wow","yes"));
        meta.add(new Meta("wow","no"));
        meta.add(new Meta("wow","maybe"));
        return meta;
    }
}

