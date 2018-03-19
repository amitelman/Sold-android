package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class IdLabelParser extends GeneralParser<IdLabel> {

    public IdLabelParser() {
        super();
    }

    @Override
    public IdLabel parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        IdLabel idLabel = null;
        idLabel = getObjectFromCache(jo,"id");
        if(idLabel == null){
            idLabel.setId(safeParseLong(jo, "id"));
        }

        idLabel.setLabel(safeParseString(jo, "name"));


        return idLabel;
    }

    @Override
    protected Class getType() {
        return IdLabel.class;
    }
}

