package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class PropertyParser extends GeneralParser<Property2> {


    public PropertyParser() {
        super();
    }


    @Override
    public Property2 parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Property2 property = new Property2();

        property = getObjectFromCache(jo,safeParseString(jo, "id"));
        if(property == null){
            property.setId(safeParseLong(jo, "id"));
        }

//        property.setLabel(safeParseString(jo, labelParseString));

        return property;
    }

    @Override
    protected Class getType() {
        return Property2.class;
    }
}

