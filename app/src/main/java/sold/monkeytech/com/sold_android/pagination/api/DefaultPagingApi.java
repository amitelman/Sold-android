package sold.monkeytech.com.sold_android.pagination.api;

import android.content.Context;


import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by elitu on 7/23/15.
 */
public abstract class DefaultPagingApi<T> extends PagingApi<T> {

    private static final int MAX_ITEMS = 20;
    private int numOfRows = 20;
    private int curRow = 0 ;
    private boolean shouldLoadMore=true;

    public DefaultPagingApi(Context context) {
        super(context);
    }

    @Override
    public PagingApi request(TAction<T> onSuccess, TAction<String> onFail) {
        next = getInitialAddress();
        next(onSuccess, onFail);
        return this;
    }

    @Override
    public void next(final TAction<T> onSuccess, final TAction<String> onFail, ParamBuilder params) {
        execute(new Runnable() {
            @Override
            public void run() {
                if (shouldLoadMore) {
                    ParamBuilder params = new ParamBuilder();
                    onBuildParams(params);
                    RemoteResponseString remoteResponseString = performHTTPPost(next,params, null);
                    if (remoteResponseString.isSuccess()) {
                        executeAdditionalAction(remoteResponseString.isSuccess());
                        JSONArray ja = null;
                        List items = new ArrayList();
//                        items = getParser().parseToList(remoteResponseString.getMessage());
//                        curRow = 1;
//                    else
                        curRow += numOfRows;

                        onPreSuccess((T) items);
                        if (onSuccess != null)
                            onSuccess.execute((T) items);
                        if(items.size() < MAX_ITEMS)
                            shouldLoadMore=false;

                    } else {
                        if (onFail != null)
                            onFail.execute(remoteResponseString.getMessage());
                    }
                }
//                else if (onSuccess!=null)
//                    onSuccess.execute((T)new ArrayList<>());
            }
        });
    }

    public void onBuildParams(ParamBuilder params){}
    public void onPreSuccess(T items){}
    public abstract String getInitialAddress();
    public abstract GeneralParser getParser();


    public abstract void executeAdditionalAction(boolean isSuccess);

}
