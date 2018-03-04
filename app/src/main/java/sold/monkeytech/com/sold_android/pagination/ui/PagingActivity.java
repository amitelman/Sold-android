package sold.monkeytech.com.sold_android.pagination.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;

import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.pagination.api.PagingApi;
import sold.monkeytech.com.sold_android.pagination.injection.Paginatable;
import sold.monkeytech.com.sold_android.pagination.injection.PaginationInjection;
import sold.monkeytech.com.sold_android.ui.adapters.abs.PagingAdapter;


/**
 * Created by elitu on 6/28/15.
 */
public abstract class PagingActivity<T,V extends AbsListView> extends BaseActivity implements Paginatable<T,V> {
    PaginationInjection paginationInjection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getLayout());



    }
    public void initInjector(){
        paginationInjection = new PaginationInjection<T,V>(){

            @Override
            public SwipeRefreshLayout getRefresher() {
                Log.d("wowPagination","getRefresher");
                return PagingActivity.this.getRefresher();
            }

            @Override
            public PagingAdapter getNewPagingAdapter(T items) {
                Log.d("wowPagination","getNewPagingAdapter");
                return PagingActivity.this.getNewPagingAdapter(items);
            }

            @Override
            public PagingApi getNewApi() {
                Log.d("wowPagination","getNewApi");
                return PagingActivity.this.getNewApi();
            }

            @Override
            public V getListView() {
                Log.d("wowPagination","getListView");
                return PagingActivity.this.getListView();
            }

            @Override
            public void onPreLoadedAction() {
                Log.d("wowPagination","onPreLoadedAction");
                PagingActivity.this.onPreLoadedAction();
            }

            @Override
            public void onPostLoadedAction() {
                Log.d("wowPagination","onPostLoadedAction");
                PagingActivity.this.onPostLoadedAction();
            }

//            @Override
//            public void filterByGender(int value) {
//                PagingActivity.this.filterByGender(value);
//            }
        };
        paginationInjection.init(shouldBlockFirst());
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener){
        if(onScrollListener!=null && paginationInjection!=null){
            paginationInjection.setOnScrollListener(onScrollListener);
        }
    }
//    protected abstract int getLayout();

    public boolean shouldBlockFirst(){
        return false;
    }


}
