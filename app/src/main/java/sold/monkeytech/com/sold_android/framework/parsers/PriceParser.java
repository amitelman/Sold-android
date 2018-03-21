package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class PriceParser extends GeneralParser<Price> {

    public PriceParser() {
        super();
    }

    @Override
    public Price parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Price price = new Price();

        price.setFormatted(safeParseString(jo, "formatted"));
        price.setValue(safeParseInt(jo, "value"));
        if(!jo.isNull("rounded"))
            price.setRounded(safeParseString(jo, "rounded"));

        return price;
    }

    @Override
    protected Class getType() {
        return Price.class;
    }
}

