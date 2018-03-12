package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class PropertyTypeParser extends GeneralParser<PropertyType> {

    public PropertyTypeParser() {
        super();
    }

    @Override
    public PropertyType parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        PropertyType propertyType = null;
        propertyType = getObjectFromCache(jo,"id");
        if(propertyType == null){
            propertyType.setId(safeParseLong(jo, "id"));
        }


        propertyType.setName(safeParseString(jo, "name"));
        propertyType.setIcon(safeParseString(jo, "icon"));
        propertyType.setCreatedAt(safeParseString(jo, "created_at"));

        return propertyType;
    }

    @Override
    protected Class getType() {
        return PropertyType.class;
    }
}

