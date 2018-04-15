package sold.monkeytech.com.sold_android.framework.serverapi.utils;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.models.Address;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.parsers.AddressParser;
import sold.monkeytech.com.sold_android.framework.parsers.LocationParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetLocation extends AbstractServerApiConnector {

    private static final int CITY = 0;
    private static final int STREET = 1;

    public ApiGetLocation(Context context) {
        super(context);
    }

    public synchronized void request(final String input, final int cityId, final int type, final TAction<List<Location>> onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                params.addText("q", input.replace(" ", "+"));
                if(type == STREET)
                    params.addInt("city_id", cityId);
                RemoteResponseString remoteResponseString = performHTTPGet("/utils/" + (type == CITY ? "cities" : "streets") , params);
                if (remoteResponseString.isSuccess()) {
                    List<Location> addressList = new LocationParser().parseToList(remoteResponseString.getMessage());
                    if(onSuccess!=null)
                        onSuccess.execute(addressList);
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        });
    }
}