package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetMe extends AbstractServerApiConnector {

    public ApiGetMe(Context context) {
        super(context);
    }

    public synchronized void request() {
        request(null,null);
    }

    public synchronized void request(final TAction<User> onSuccess, final TAction<String> onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();
                RemoteResponseString remoteResponseString = performHTTPGet("users/me", params);
                if (remoteResponseString.isSuccess()) {
                    JSONObject jo = null;
                    try {
                        jo = new JSONObject(remoteResponseString.getMessage());
                        UserManager.getInstance().setInAppToken(jo.getString("in_app_token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    UserManager.getInstance().setCurrentUser(new UserParser().parseToSingle(remoteResponseString.getMessage()));
                    User user  = null; //new UserParser().parseToSingle(remoteResponseString.getMessage());
//                    UserManager.getInstance().setCurrentUser(user);
//                    UserManager.getInstance().setInAppToken(remoteResponseString.getMessage().getString("in_app_token"));
                    if(onSuccess!=null)
                        onSuccess.execute(user);
                } else {
                    if(onFail!=null)
                        onFail.execute(remoteResponseString.getMessage());
                }
            }
        });
    }
}