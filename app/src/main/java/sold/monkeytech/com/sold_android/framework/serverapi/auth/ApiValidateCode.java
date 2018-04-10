package sold.monkeytech.com.sold_android.framework.serverapi.auth;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;

import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.parsers.UserParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiValidateCode extends AbstractServerApiConnector {

    public ApiValidateCode(Context context) {
        super(context);
    }

    public synchronized void request(final String phone, final String code, final Action onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                params.addText("phone_number", phone)
                        .addText("code", code);
                RemoteResponseString remoteResponseString = performHTTPPost("/auth/validate_code", params);

                if (remoteResponseString.isSuccess()) {
                    try {
                        JSONObject responseObj = new JSONObject(remoteResponseString.getMessage());
                        User user = new UserParser().parseToSingle(responseObj);
                        UserManager.getInstance().setCurrentUser(user);
                        UserManager.getInstance().setInAppToken(responseObj.getString("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
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