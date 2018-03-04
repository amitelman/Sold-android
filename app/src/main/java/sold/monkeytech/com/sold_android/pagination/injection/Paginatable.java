package sold.monkeytech.com.sold_android.pagination.injection;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;

import sold.monkeytech.com.sold_android.pagination.api.PagingApi;
import sold.monkeytech.com.sold_android.ui.adapters.abs.PagingAdapter;


/**
 * Created by elitu on 8/4/15.
 */
public interface Paginatable<T,V extends AbsListView> {
    abstract SwipeRefreshLayout getRefresher();
    public abstract PagingAdapter getNewPagingAdapter(T items);
    public abstract PagingApi getNewApi();
    abstract V getListView();
    abstract void onPreLoadedAction();
    abstract void onPostLoadedAction();

//    public abstract void filterByGender(int value);
}
