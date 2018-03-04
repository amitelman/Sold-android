package sold.monkeytech.com.sold_android.pagination.injection;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.SoldApplication;
import sold.monkeytech.com.sold_android.pagination.api.PagingApi;
import sold.monkeytech.com.sold_android.pagination.utils.EndlessScrollListener;
import sold.monkeytech.com.sold_android.ui.adapters.abs.PagingAdapter;


/**
 * Created by elitu on 6/28/15.
 */
public abstract class PaginationInjection<T,V extends AbsListView> implements Paginatable<T,V>{
    PagingApi<T> api;
    PagingAdapter<T> adapter;
    boolean alreadyLoaded=false;
    private AbsListView.OnScrollListener onScrollListener;
    public void init(boolean shouldBlockFirst){
        final Handler handler = new Handler();
        getLoadAction(handler,shouldBlockFirst).execute();
        if (getRefresher() != null && !shouldBlockFirst)
            getRefresher().setRefreshing(true);
        if (getRefresher() != null)
            getRefresher().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getLoadAction(handler).execute();
                }
            });

    }

    public void init(){
        init(false);
    }

    protected void loadMoreData(int page) {
        if(api!=null) {
            final Handler handler = new Handler();
            api.next(new TAction<T>() {
                @Override
                public void execute(final T items) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (adapter != null) {
                                Toast.makeText(SoldApplication.getContext(), "loading more!", Toast.LENGTH_SHORT).show();
                                adapter.addMoreItems(items);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }, new TAction<String>() {
                @Override
                public void execute(String param) {

                }
            });
        }
    }
    private void setScrollListener(V listView){
        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadMoreData(page);
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                super.onScroll(absListView,i,i1,i2);
                if (onScrollListener!=null)
                    onScrollListener.onScroll(absListView,i,i1,i2);
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (onScrollListener!=null)
                    onScrollListener.onScrollStateChanged(view,scrollState);
            }
        });
    }
    public Action getLoadAction(final Handler handler){
        return getLoadAction(handler,false);
    }
    private Action getLoadAction(final Handler handler, final boolean shouldBlock){
        final TAction<T> onSuccess = new TAction<T>() {
            @Override
            public void execute(final T posts) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = getNewPagingAdapter(posts);
                        V listView = getListView();
                        View view = null;
//                        try {
//                            view = getView().findViewById(R.id.error_fragment_layout);
//                        }catch (Exception e){}
                        if(listView!=null) {
                            listView.setAdapter(adapter);
                            setScrollListener(listView);
                            if(getRefresher()!=null)
                                getRefresher().setRefreshing(false);
                            onPostLoadedAction();
                        }
                    }
                });
            }
        };

        final TAction<String> onFail = new TAction<String>() {
            @Override
            public void execute(String error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (getRefresher() != null)
                            getRefresher().setRefreshing(false);
                        onPostLoadedAction();
                    }
                });
                V listView = getListView();
                setScrollListener(listView);
//                adapter
            }
        };

        final Action loadAction = new Action() {
            @Override
            public void execute() {
                if(alreadyLoaded)
                    //LocManager.getInstance().getLatLng();
                    alreadyLoaded=true;

                    api = getNewApi().request(onSuccess, onFail);
                onPreLoadedAction();

            }
        };
        return loadAction;
    }


    public PagingAdapter<T> getAdapter() {
        return adapter;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public AbsListView.OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }


    public void refresh(){
        if(getRefresher()!=null) {
            getLoadAction(new Handler()).execute();
        }
    }
}
