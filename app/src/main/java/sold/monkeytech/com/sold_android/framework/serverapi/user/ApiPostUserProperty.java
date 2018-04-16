package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiPostUserProperty extends AbstractServerApiConnector {

    public ApiPostUserProperty(Context context) {
        super(context);
    }

    public synchronized void request(final int streetId, final String houseNumber, final String apt, final int floor, final String description,
                                     final int meetUpTime, final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                params.addInt("street_id", streetId)
                        .addText("house_number", houseNumber)
                        .addText("apt", apt)
                        .addInt("floor", floor)
                        .addText("description", description)
                        .addInt("preferred_meetup_time", meetUpTime);

                RemoteResponseString remoteResponseString = performHTTPPost("/users/me/properties", params);
                if (remoteResponseString.isSuccess()) {
                    if (onSuccess != null)
                        onSuccess.execute();
                } else {
                    if (onFail != null)
                        onFail.execute();
                }
            }
        }));
    }
}