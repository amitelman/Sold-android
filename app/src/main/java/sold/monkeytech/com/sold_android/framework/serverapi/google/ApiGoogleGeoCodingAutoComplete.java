package sold.monkeytech.com.sold_android.framework.serverapi.google;

import android.content.Context;

import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGoogleGeoCodingAutoComplete extends AbstractServerApiConnector {

    private final Context context;

    public ApiGoogleGeoCodingAutoComplete(Context context) {
        super(context);
        this.context = context;
    }

    public synchronized void request(final String q, final TAction<String> onSuccess, final TAction<String> onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + q + "&key=" + context.getResources().getString(R.string.google_maps_api_key);
                RemoteResponseString remoteResponseString = performSpecificHTTPPost(url);
                if (remoteResponseString.isSuccess()) {

                    if(onSuccess!=null)
                        onSuccess.execute("");
                } else {
                    if(onFail!=null)
                        onFail.execute(remoteResponseString.getMessage());
                }
            }
        });
    }
}