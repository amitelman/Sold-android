package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class LocationParser extends GeneralParser<Location> {

    public LocationParser() {
        super();
    }

    @Override
    public Location parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Location location = new Location();

        location.setId(safeParseLong(jo, "id"));
        location.setLocationName(safeParseString(jo, "name"));
        if(jo.has("type"))
            location.setLocationType(safeParseString(jo, "type"));

        return location;
    }

    @Override
    protected Class getType() {
        return Location.class;
    }
}

