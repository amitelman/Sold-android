package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class POIParser extends GeneralParser<POI> {

    public POIParser() {
        super();
    }

    @Override
    public POI parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        POI poi = new POI();

        poi.setName(safeParseString(jo, "name"));
        poi.setDescription(safeParseString(jo, "description"));
        poi.setAddress(safeParseString(jo, "address"));
        poi.setLat(safeParseString(jo, "lat"));
        poi.setLng(safeParseString(jo, "lng"));
        poi.setRating(safeParseInt(jo, "rating"));
        poi.setCategory(new CategoryParser().parseToSingle(jo.getJSONObject("category")));

        return poi;
    }

    @Override
    protected Class getType() {
        return POI.class;
    }
}

