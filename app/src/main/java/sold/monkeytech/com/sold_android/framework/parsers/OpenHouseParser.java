package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class OpenHouseParser extends GeneralParser<OpenHouse> {

    public OpenHouseParser() {
        super();
    }

    @Override
    public OpenHouse parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        OpenHouse openHouse = null;
        openHouse = getObjectFromCache(jo,"id");
        if(openHouse == null){
            openHouse = new OpenHouse();
            openHouse.setId(safeParseLong(jo, "id"));
        }

        openHouse.setWday(safeParseString(jo, "wday"));
        openHouse.setDay(safeParseInt(jo, "day"));
        openHouse.setMonth(safeParseString(jo, "month"));


        openHouse.setSlots(new OpenHouseSlotsParser().parseToList(jo.getJSONArray("slots")));

        return openHouse;
    }

    @Override
    protected Class getType() {
        return OpenHouse.class;
    }
}

