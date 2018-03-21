package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class ServicePageParser extends GeneralParser<ServicePage> {

    public ServicePageParser() {
        super();
    }

    @Override
    public ServicePage parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        ServicePage servicePage = null;
        servicePage = getObjectFromCache(jo,"id");
        if(servicePage == null){
            servicePage = new ServicePage();
            servicePage.setId(safeParseLong(jo, "id"));
        }

        servicePage.setTitle(safeParseString(jo, "title"));
        servicePage.setContent(safeParseString(jo, "content"));
        servicePage.setImage(safeParseString(jo, "image"));


        return servicePage;
    }

    @Override
    protected Class getType() {
        return ServicePage.class;
    }
}

