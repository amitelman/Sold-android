package sold.monkeytech.com.sold_android.framework.serverapi.user;

import android.content.Context;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.models.SavedSearch;
import sold.monkeytech.com.sold_android.framework.parsers.SavedSearchParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by monkey on 08/06/2015.
 */
public class ApiGetSavedSearch extends AbstractServerApiConnector {

    public ApiGetSavedSearch(Context context) {
        super(context);
    }

    public synchronized void request(final TAction<List<SavedSearch>> onSuccess, final Action onFail) {
        execute(new Runnable() {
            @Override
            public void run() {
                ParamBuilder params = new ParamBuilder();

                RemoteResponseString remoteResponseString = performHTTPGet("/users/me/searches", params);
                if (remoteResponseString.isSuccess()) {
                    List<SavedSearch> savedSearches = new SavedSearchParser().parseToList(remoteResponseString.getMessage());
                    if (onSuccess != null)
                        onSuccess.execute(savedSearches);
                } else {
                    if (onFail != null)
                        onFail.execute();
                }
            }
        });
    }
}