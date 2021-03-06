package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetMyPropertyById extends AbstractServerApiConnector {

    public ApiGetMyPropertyById(Context context) {
        super(context);
    }

    public synchronized void request(int propertyId) {
        request(propertyId, null,null);
    }

    public synchronized void request(final long propertyId, final TAction<Property> onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPGet("/users/me/properties/" + propertyId, params);
                Property property = null;
                if (remoteResponseString.isSuccess()) {
                    property = new PropertyParser(PropertyParser.TYPE_FULL).parseToSingle(remoteResponseString.getMessage());
                    if(onSuccess!=null)
                        onSuccess.execute(property);
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        });
    }
}