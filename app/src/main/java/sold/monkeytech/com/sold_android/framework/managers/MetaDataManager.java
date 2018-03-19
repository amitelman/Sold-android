package sold.monkeytech.com.sold_android.framework.managers;

import com.pixplicity.easyprefs.library.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.parsers.IdLabelParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyFeaturesParser;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyTypeParser;


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
}

