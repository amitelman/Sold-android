package sold.monkeytech.com.sold_android.pagination.api;

import android.content.Context;
import android.util.Log;

import com.monkeytechy.framework.interfaces.TAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.RemoteResponseString;
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.ParamBuilder;

/**
 * Created by elitu on 7/23/15.
 */
public abstract class SearchPagingApi<T> extends PagingApi<T> {

    private int CurrentPageNumber = 0 ;
    private boolean shouldLoadMore=true;

    private HashMap<String, Integer> properties;
    private boolean isFirstTime = true;

    private boolean bla = false;

    public SearchPagingApi(Context context) {
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

//                    updatePageQueryModelForPagination();

                    try {
                        Log.d("wowParser","current searchId: " + getPagedQueryModel().get("SearchId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RemoteResponseString remoteResponseString = performHTTPPostWithJSON(next, getPagedQueryModel());

                    if (remoteResponseString.isSuccess()) {
                    try {
                        executeAdditionalAction(remoteResponseString.isSuccess());

                        JSONObject jsonObject = new JSONObject(remoteResponseString.getMessage());
                        JSONArray ja = jsonObject.getJSONArray("Stones");
//                        List<Stone> stones = getParser().parseToList(ja);
//                        parseSearchProperties(jsonObject);
//                        SearchObject searchObject = new SearchObject(properties, stones);

//                        updatePageQueryModelSearchId(searchObject);

//                        PagedQueryManager.getInstance().lockPagedQueryObjectToCurSearch();

//                        onSuccess.execute((T) searchObject);
//                        Log.d("wowParser","onSuccess recieved searchId: " + searchObject.getProperties().get("SearchId"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else
                    onFail.execute(remoteResponseString.getMessage());
                }
            }
        });
    }


//    private void updatePageQueryModelForPagination() {
//        if(isFirstTime){
//            isFirstTime = false;
//            return;
//        }
//        String curPage = PagedQueryManager.getInstance().getPagedQueryObjectCurrentPage();
//        int curPageInt = Integer.parseInt(curPage);
//        PagedQueryManager.getInstance().updatePagedQueryObjectCurrentPage(curPageInt + 1);
//        Log.d("wowPegination","updated page to: " + curPageInt);
//    }

//    private void parseSearchProperties(JSONObject jo) {
//        properties = new HashMap<>();
//        try {
//            properties.put("SearchId",jo.getInt("SearchId"));
//            Log.d("wowParser","onSuccess SearchId: " + jo.getInt("SearchId"));
//            properties.put("TotalCount",jo.getInt("TotalCount"));
//            properties.put("TotalPagesCount",jo.getInt("TotalPagesCount"));
//            properties.put("CurrentPageNumber",jo.getInt("CurrentPageNumber"));
//            properties.put("PageSize",jo.getInt("PageSize"));
//            properties.put("TotalDisplayRecords",jo.getInt("TotalDisplayRecords"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void onBuildParams(ParamBuilder params){}
    public void onPreSuccess(T items){}
    public abstract String getInitialAddress();
    public abstract GeneralParser getParser();
    public abstract JSONObject getPagedQueryModel();

    public abstract void executeAdditionalAction(boolean isSuccess);

}
