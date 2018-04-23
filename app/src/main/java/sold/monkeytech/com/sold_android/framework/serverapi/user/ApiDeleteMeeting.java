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
public class ApiDeleteMeeting extends AbstractServerApiConnector {

    public ApiDeleteMeeting(Context context) {
        super(context);
    }

    public synchronized void request(final int propertyId, final long meetingId,  final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPDelete("/users/me/properties/" + propertyId + "/meetings/" + meetingId, params);
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