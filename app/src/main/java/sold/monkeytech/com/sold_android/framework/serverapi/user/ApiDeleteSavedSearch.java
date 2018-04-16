package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.SavedSearch;
import sold.monkeytech.com.sold_android.framework.parsers.SavedSearchParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.ServerAction;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiDeleteSavedSearch extends AbstractServerApiConnector {

    public ApiDeleteSavedSearch(Context context) {
        super(context);
    }

    public synchronized void request(final String deletedCsv, final Action onSuccess, final Action onFail) {
        setServerAction(true, new ServerAction(new Action() {
            @Override
            public void execute() {
                ParamBuilder params = new ParamBuilder();
                params.addText("search_ids",deletedCsv);
                RemoteResponseString remoteResponseString = performHTTPDelete("/users/me/searches", params);
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