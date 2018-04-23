package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiPostLead extends AbstractServerApiConnector {

    public ApiPostLead(Context context) {
        super(context);
    }

    public synchronized void request(final int propertyId, final int slotId, final String description, final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                if(propertyId != 0)
                    params.addInt("property_id", propertyId);
                if(slotId != 0)
                    params.addInt("slot_id", slotId);
                if(!TextUtils.isEmpty(description))
                    params.addText("notes", description);

                RemoteResponseString remoteResponseString = performHTTPPost("/users/me/leads", params);
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