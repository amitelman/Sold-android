package sold.monkeytech.com.sold_android.framework.managers;

import android.content.Context;

import com.monkeytechy.framework.interfaces.TAction;
import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;
import sold.monkeytech.com.sold_android.framework.parsers.IdLabelParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyFeaturesParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyTypeParser;
import sold.monkeytech.com.sold_android.framework.parsers.ServicePageParser;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;


/**
 * Created by MonkeyFather on 20/08/2017.
 */

public class MetaDataManager {

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

    public List<Meta> getMetaDataMap(){
        List<Meta> meta = new ArrayList<>();
        meta.add(new Meta("wow","yes"));
        meta.add(new Meta("wow","no"));
        meta.add(new Meta("wow","maybe"));
        return meta;
    }
}

