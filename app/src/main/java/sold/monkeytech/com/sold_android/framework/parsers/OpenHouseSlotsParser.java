package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class OpenHouseSlotsParser extends GeneralParser<OpenHouseSlots> {

    public OpenHouseSlotsParser() {
        super();
    }

    @Override
    public OpenHouseSlots parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        OpenHouseSlots openHouse = null;
        openHouse = getObjectFromCache(jo,"id");
        if(openHouse == null){
            openHouse.setId(safeParseLong(jo, "id"));
        }

        openHouse.setName(safeParseString(jo, "name"));

        return openHouse;
    }

    @Override
    protected Class getType() {
        return OpenHouseSlots.class;
    }
}

