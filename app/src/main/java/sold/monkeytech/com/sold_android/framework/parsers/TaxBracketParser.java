package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.TaxBracket;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class TaxBracketParser extends GeneralParser<TaxBracket> {

    public TaxBracketParser() {
        super();
    }

    @Override
    public TaxBracket parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        TaxBracket taxBracket = null;
        taxBracket = getObjectFromCache(jo,"id");
        if(taxBracket == null){
            taxBracket = new TaxBracket();
            taxBracket.setId(safeParseLong(jo, "id"));
        }

        taxBracket.setPropertiesCount(safeParseInt(jo, "properties_count"));
        taxBracket.setTaxRate(safeParseString(jo, "tax_rate"));
        taxBracket.setMinPrice(new PriceParser().parseToSingle(jo.getJSONObject("min_price")));
        if(!jo.isNull("max_price"))
            taxBracket.setMaxPrice(new PriceParser().parseToSingle(jo.getJSONObject("max_price")));


        return taxBracket;
    }

    @Override
    protected Class getType() {
        return TaxBracket.class;
    }
}

