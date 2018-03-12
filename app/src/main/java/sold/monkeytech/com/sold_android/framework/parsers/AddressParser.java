package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Address;
import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class AddressParser extends GeneralParser<Address> {

    public AddressParser() {
        super();
    }

    @Override
    public Address parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Address address = new Address();
        address.setStreetId(safeParseLong(jo, "id"));
        address.setStreetName(safeParseString(jo, "name"));
        address.setCityName(safeParseString(jo.getJSONObject("city"),"name"));
        address.setCityId(safeParseLong(jo.getJSONObject("city"), "id"));

        return address;
    }

    @Override
    protected Class getType() {
        return Address.class;
    }
}

