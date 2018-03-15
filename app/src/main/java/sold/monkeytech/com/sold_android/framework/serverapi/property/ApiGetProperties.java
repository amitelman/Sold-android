package sold.monkeytech.com.sold_android.framework.serverapi.property;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.parsers.PropertyParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;
import sold.monkeytech.com.sold_android.pagination.abs.PagibaleApi;

/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetProperties extends AbstractServerApiConnector implements PagibaleApi<Integer,List<Property>,String> {

    private ParamBuilder params;
    private HashMap<String, Integer> properties;
    private FragmentActivity fragmentActivity;

    public ApiGetProperties(Context context) {
        super(context);
        if(params != null)
            this.params = params;
    }


    @Override
    public void getNext(Integer pageData, final TAction<List<Property>> onSuccess, final TAction<String> onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();

                RemoteResponseString remoteResponseString = performHTTPGet("/properties", SearchParamManager.getInstance().getParams());

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
                } else{
                    onFail.execute(remoteResponseString.getMessage());

                }
            }
        });
    }

}
