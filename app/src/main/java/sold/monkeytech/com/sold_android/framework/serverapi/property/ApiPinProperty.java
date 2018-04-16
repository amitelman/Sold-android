package sold.monkeytech.com.sold_android.framework.serverapi.property;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiPinProperty extends AbstractServerApiConnector {

    public ApiPinProperty(Context context) {
        super(context);
    }

    public synchronized void request(int propertyId) {
        request(propertyId, null,null);
    }

    public synchronized void request(final long propertyId, final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPPost("/properties/" + propertyId + "/pin", params);
                if (remoteResponseString.isSuccess()) {
                    if(onSuccess!=null)
                        onSuccess.execute();
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        }));
    }
}