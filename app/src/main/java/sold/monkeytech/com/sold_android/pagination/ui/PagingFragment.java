package sold.monkeytech.com.sold_android.pagination.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;

import sold.monkeytech.com.sold_android.pagination.api.PagingApi;
import sold.monkeytech.com.sold_android.pagination.injection.Paginatable;
import sold.monkeytech.com.sold_android.pagination.injection.PaginationInjection;
import sold.monkeytech.com.sold_android.ui.adapters.abs.PagingAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;


/**
 * Created by elitu on 6/28/15.
 */
public abstract class PagingFragment<T,V extends AbsListView> extends BaseFragment implements Paginatable<T,V> {
    protected PaginationInjection paginationInjection;
    @Override
    public void onLoaded() {
        super.onLoaded();
        paginationInjection = new PaginationInjection<T,V>(){

            @Override
            public SwipeRefreshLayout getRefresher() {
                return PagingFragment.this.getRefresher();
            }

            @Override
            public PagingAdapter getNewPagingAdapter(T items) {
                return PagingFragment.this.getNewPagingAdapter(items);
            }

            @Override
            public PagingApi getNewApi() {
                return PagingFragment.this.getNewApi();
            }

            @Override
            public V getListView() {
                return PagingFragment.this.getListView();
            }
            @Override
            public void onPreLoadedAction() {
                PagingFragment.this.onPreLoadedAction();
            }

            @Override
            public void onPostLoadedAction() {
                PagingFragment.this.onPostLoadedAction();
            }

        };
        paginationInjection.init();
    }
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener){
        if(onScrollListener!=null && paginationInjection!=null){
            paginationInjection.setOnScrollListener(onScrollListener);
        }
    }

    public void refresh(){
        if(paginationInjection!=null) {
            paginationInjection.refresh();
        }
    }
}
