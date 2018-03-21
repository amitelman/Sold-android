package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.TaxRecord;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class TaxRecordParser extends GeneralParser<TaxRecord> {


    public TaxRecordParser() {
        super();
    }


    @Override
    public TaxRecord parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        TaxRecord taxRecord = null;

        taxRecord = getObjectFromCache(jo,"id");
        if(taxRecord == null){
            taxRecord = new TaxRecord();
            taxRecord.setId(safeParseLong(jo, "id"));
        }

        taxRecord.setYear(safeParseInt(jo, "year"));
        taxRecord.setDescription(safeParseString(jo, "description"));
        taxRecord.setAmount(new PriceParser().parseToSingle(jo.getJSONObject("amount")));

        return taxRecord;
    }

    @Override
    protected Class getType() {
        return TaxRecord.class;
    }
}

