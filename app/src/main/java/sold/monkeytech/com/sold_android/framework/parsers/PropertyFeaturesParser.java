package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class PropertyFeaturesParser extends GeneralParser<PropertyFeatures> {

    public PropertyFeaturesParser() {
        super();
    }

    @Override
    public PropertyFeatures parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        PropertyFeatures propertyFeatures = null;
        propertyFeatures = getObjectFromCache(jo,"id");
        if(propertyFeatures == null){
            propertyFeatures = new PropertyFeatures();
            propertyFeatures.setId(safeParseLong(jo, "id"));
        }

        propertyFeatures.setFeatureName(safeParseString(jo.getJSONObject("feature"), "name"));
        propertyFeatures.setMeta(new MetaParser().parseToList(jo.getJSONArray("meta")));

        JSONArray images = jo.getJSONArray("images");
        List<String> imagesArr = new ArrayList<>();
        for (int i = 0; i < images.length(); i++) {
            String link = images.getString(i);
            imagesArr.add(link);
        }
        propertyFeatures.setImages(imagesArr);

        return propertyFeatures;
    }

    @Override
    protected Class getType() {
        return PropertyFeatures.class;
    }
}

