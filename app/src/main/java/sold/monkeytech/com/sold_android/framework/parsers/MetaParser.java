package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class MetaParser extends GeneralParser<Meta> {

    public MetaParser() {
        super();
    }

    @Override
    public Meta parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Meta meta = new Meta();

        meta.setKey(safeParseString(jo, "key"));
        meta.setValue(safeParseString(jo, "value"));

        return meta;
    }

    @Override
    protected Class getType() {
        return Meta.class;
    }
}

