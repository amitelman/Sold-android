package sold.monkeytech.com.sold_android.pagination.api;

import android.content.Context;

import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;


/**
 * Created by elitu on 6/28/15.
 */
public abstract class PagingApi<T>  extends AbstractServerApiConnector {
    protected String next;
    public PagingApi(Context context) {
        super(context);
    }

    public void next(TAction<T> onSuccess , TAction<String> onFail){
        next(onSuccess,onFail,null);
    }
    public abstract void next(TAction<T> onSuccess , TAction<String> onFail, ParamBuilder params);
    public abstract PagingApi request(TAction<T> onSuccess , TAction<String> onFail);


}
