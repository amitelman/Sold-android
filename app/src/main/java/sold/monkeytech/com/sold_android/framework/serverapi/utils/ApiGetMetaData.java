package sold.monkeytech.com.sold_android.framework.serverapi.utils;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetMetaData extends AbstractServerApiConnector {

    public ApiGetMetaData(Context context) {
        super(context);
    }

    public synchronized void request() {
        request(null,null);
    }

    public synchronized void request(final Action onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPGet("/utils/meta", params);
                if (remoteResponseString.isSuccess()) {
                    try {
                        MetaDataManager.getInstance().setMetaData(new JSONObject(remoteResponseString.getMessage()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(onSuccess!=null)
                        onSuccess.execute();
                } else {
                    if(onFail!=null)
                        onFail.execute();
                }
            }
        });
    }
}