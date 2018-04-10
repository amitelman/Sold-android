package sold.monkeytech.com.sold_android.framework.serverapi.auth;

import android.content.Context;
import android.text.TextUtils;

import com.monkeytechy.framework.interfaces.Action;

import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetCode extends AbstractServerApiConnector {

    public ApiGetCode(Context context) {
        super(context);
    }

    public synchronized void request(final String phone, final String first, final String last, final String email, final Action onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                params.addText("phone_number", phone);
                if(!TextUtils.isEmpty(first)){
                    params.addText("first_name", first)
                            .addText("last_name", last)
                            .addText("email", email);
                }
                RemoteResponseString remoteResponseString = performHTTPPost("/auth/get_code", params);
                if (remoteResponseString.isSuccess()) {
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