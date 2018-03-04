//package sold.monkeytech.com.sold_android.pagination.api;
//
//import android.content.Context;
//
//import com.monkeytechy.framework.interfaces.TAction;
//
//import java.util.List;
//
//import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
//import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;
//
//import static sold.monkeytech.com.sold_android.framework.serverapi.abs.AbstractServerApiConnector.execute;
//
///**
// * Created by elitu on 7/23/15.
// */
//public abstract class DefaultHeaderPagingApi<T> extends PagingApi<T> {
//    public DefaultHeaderPagingApi(Context context) {
//        super(context);
//    }
//
//    @Override
//    public PagingApi request(TAction<T> onSuccess, TAction<String> onFail) {
//        next = getInitialAddress();
//        next(onSuccess, onFail);
//        return this;
//    }
//
//    @Override
//    public void next(final TAction<T> onSuccess, final TAction<String> onFail, ParamBuilder params) {
//        execute(new Runnable() {
//            @Override
//            public void run() {
//                ParamBuilder params = new ParamBuilder();
//                onBuildParams(params);
//                RemoteResponseString remoteResponseString = performHTTPPost(next,params, null);
//                if (remoteResponseString.isSuccess()) {
//                    List items = getParser().parseToList(remoteResponseString.getMessage());
////                    next = remoteResponseString.getHeaders().get("RowNumber");
//                    onPreSuccess((T) items);
//                    if (onSuccess != null)
//                        onSuccess.execute((T) items);
//
//                } else {
//                    if (onFail != null)
//                        onFail.execute(remoteResponseString.getMessage());
//                }
//            }
//        });
//    }
//
//    public void onBuildParams(ParamBuilder params){}
//    public void onPreSuccess(T items){}
//    public abstract String getInitialAddress();
//    public abstract GeneralParser getParser();
//
//}
