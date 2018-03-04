package sold.monkeytech.com.sold_android.pagination.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;

import sold.monkeytech.com.sold_android.pagination.api.PagingApi;
import sold.monkeytech.com.sold_android.pagination.injection.Paginatable;
import sold.monkeytech.com.sold_android.pagination.injection.PaginationInjection;
import sold.monkeytech.com.sold_android.ui.adapters.abs.PagingAdapter;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;


/**
 * Created by elitu on 7/22/15.
 */
public abstract class PagingDialog<T,V extends AbsListView> extends TransparentDialog implements Paginatable<T,V> {
    PaginationInjection paginationInjection;
        protected PagingAdapter<T> adapter;

    public PagingDialog(Context context) {
        super(context);
    }

    public PagingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected PagingDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected PagingDialog<T,V> init(Context context) {
        paginationInjection = new PaginationInjection<T,V>(){

            @Override
            public SwipeRefreshLayout getRefresher() {
                return PagingDialog.this.getRefresher();
            }

            @Override
            public PagingAdapter getNewPagingAdapter(T items) {
                return adapter = PagingDialog.this.getNewPagingAdapter(items);
            }

            @Override
            public PagingApi getNewApi() {
                return PagingDialog.this.getNewApi();
            }

            @Override
            public V getListView() {
                return PagingDialog.this.getListView();
            }
            @Override
            public void onPreLoadedAction() {
                PagingDialog.this.onPreLoadedAction();
            }

            @Override
            public void onPostLoadedAction() {
                PagingDialog.this.onPostLoadedAction();
            }

        };
        paginationInjection.init();
        return this;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener){
        if(onScrollListener!=null && paginationInjection!=null){
            paginationInjection.setOnScrollListener(onScrollListener);
        }
    }
    @Override
    public void show(){
        super.show();
        init(getContext());
    }
}