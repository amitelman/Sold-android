package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetMyProperties extends AbstractServerApiConnector {

    public ApiGetMyProperties(Context context) {
        super(context);
    }

    public synchronized void request(final TAction<List<Property>> onSuccess, final TAction<String> onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPGet("/users/me/properties", params);
                if (remoteResponseString.isSuccess()) {
                    List<Property> properties = null;
                    try {
                        properties = new PropertyParser(PropertyParser.TYPE_SHORT).parseToList(new JSONArray(remoteResponseString.getMessage()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(onSuccess != null){
                        onSuccess.execute(properties);
                    }
                } else {
                    if(onFail!=null)
                        onFail.execute(remoteResponseString.getMessage());
                }
            }
        }));
    }
}