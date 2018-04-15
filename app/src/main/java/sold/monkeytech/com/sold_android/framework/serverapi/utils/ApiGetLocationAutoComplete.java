package sold.monkeytech.com.sold_android.framework.serverapi.utils;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

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
public class ApiGetLocationAutoComplete extends AbstractServerApiConnector {

    public ApiGetLocationAutoComplete(Context context) {
        super(context);
    }

    public synchronized void request(final String input, final TAction<List<Location>> onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                params.addText("q", input.replace(" ", "+"));
                RemoteResponseString remoteResponseString = performHTTPGet("/utils/locations" , params);
                if (remoteResponseString.isSuccess()) {
                    List<Location> locations = new LocationParser().parseToList(remoteResponseString.getMessage());
                    if(onSuccess!=null)
                        onSuccess.execute(locations);
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        });
    }
}