package sold.monkeytech.com.sold_android.pagination.utils;

import android.os.Handler;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.pagination.abs.PagibaleAdapter;
import sold.monkeytech.com.sold_android.pagination.abs.PagibaleApi;


/**
 * Created by elitu on 17/10/2017.
 */

public abstract class ListViewReloader<PageObject,ListType,ErrorType> {
    private final PagibaleApi api;
    private PagibaleAdapter adapter;
    private EndlessScrollListener endlessScrollListener;
    private ListView listView;
    private ListType lastApiResult;

    public ListViewReloader(ListView listView, PagibaleAdapter<ListType> adapter, PagibaleApi<PageObject,ListType,ErrorType> api){
        this.listView=listView;
        this.adapter = adapter;
        this.api = api;
    }
    public void init(int startPage){
        endlessScrollListener = new EndlessScrollListener(5,startPage) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                reloadList();
            }
        };
        listView.setAdapter((ListAdapter) (adapter));
        listView.setOnScrollListener(endlessScrollListener);
    }
    public void reloadList(){
        final Handler h= new Handler();
        api.getNext(getNextPage(),new TAction<ListType>() {
            @Override
            public void execute(final ListType param) {
                ListViewReloader.this.lastApiResult=param;
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addItems(param);
                    }
                });
            }
        }, new TAction<String>() {
            @Override
            public void execute(String param) {

            }
        });
    }
    public abstract PageObject getNextPage();
    public int getScrollPageNumber(){
        return endlessScrollListener.currentPage;
    }

    public ListType getLastApiResult() {
        return lastApiResult;
    }
}
