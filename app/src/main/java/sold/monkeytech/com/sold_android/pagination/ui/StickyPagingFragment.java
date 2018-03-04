package sold.monkeytech.com.sold_android.pagination.ui;//package com.mid.mid.pagination.ui;
//
//import android.os.Handler;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.View;
//import android.widget.AbsListView;
//
//import com.mid.mid.pagination.api.PagingApi;
//import com.mid.mid.ui.fragments.abs.BaseFragment;
//import com.monkeytechy.framework.interfaces.Action;
//import com.monkeytechy.framework.interfaces.TAction;
//
//
///**
// * Created by elitu on 6/28/15.
// */
//public abstract class StickyPagingFragment<T> extends BaseFragment {
////    @Override
////    public void onLoaded() {
////        super.onLoaded();
////        new PaginationInjection<T,V>(){
////
////            @Override
////            public SwipeRefreshLayout getRefresher() {
////                return StickyPagingFragment.this.getRefresher();
////            }
////
////            @Override
////            public PagingAdapter getNewPagingAdapter(T items) {
////                return getNewPagingAdapter(items);
////            }
////
////            @Override
////            public PagingApi getNewApi() {
////                return getNewApi();
////            }
////
////            @Override
////            public V getListView() {
////                return getListView();
////            }
////        };
////    }
////}
//    PagingApi<T> api;
//    PagingAdapter<T> adapter;
//    boolean alreadyLoaded=false;
//    private AbsListView.OnScrollListener onScrollListener;
//
//    @Override
//    public void onLoaded(){
//        super.onLoaded();
//        final Handler handler = new Handler();
//        getLoadAction(handler).execute();
//        if(getRefresher()!=null)
//            getRefresher().setRefreshing(true);
//        if(getRefresher()!=null)
//            getRefresher().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    getLoadAction(handler).execute();
//                }
//            });
//    }
//
//    protected void loadMoreData(int page) {
//        if(api!=null) {
//            final Handler handler = new Handler();
//            api.next(new TAction<T>() {
//                @Override
//                public void execute(final T items) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (adapter != null) {
//                                adapter.addMoreItems(items);
//                                adapter.notifyDataSetChanged();
//                            }
//                        }
//                    });
//                }
//            }, new TAction<String>() {
//                @Override
//                public void execute(String param) {
//
//                }
//            });
//        }
//    }
//
//    protected Action getLoadAction(final Handler handler){
//        final TAction<T> onSuccess = new TAction<T>() {
//            @Override
//            public void execute(final T posts) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter = getNewPagingAdapter(posts);
//                        StickyListHeadersListView listView = getListView();
//                        View view = null;
////                        try {
////                            view = getView().findViewById(R.id.error_fragment_layout);
////                        }catch (Exception e){}
//                        if(listView!=null) {
//                            listView.setAdapter((StickyListHeadersAdapter)adapter);
//                            listView.setOnScrollListener(new EndlessScrollListener() {
//                                @Override
//                                public void onLoadMore(int page, int totalItemsCount) {
//                                    loadMoreData(page);
//                                }
//
//                                @Override
//                                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//                                    super.onScroll(absListView, i, i1, i2);
//                                    if (onScrollListener != null)
//                                        onScrollListener.onScroll(absListView, i, i1, i2);
//                                }
//
//                                @Override
//                                public void onScrollStateChanged(AbsListView view, int scrollState) {
//                                    if (onScrollListener != null)
//                                        onScrollListener.onScrollStateChanged(view, scrollState);
//                                }
//                            });
//                            getRefresher().setRefreshing(false);
//                            onPostLoadAction();
//                        }
//                    }
//                });
//            }
//        };
//
//        final TAction<String> onFail = new TAction<String>() {
//            @Override
//            public void execute(String error) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(getView()!=null) {
////                            View pb = getView().findViewById(R.id.pb);
////                            if(pb!=null)
////                                pb.setVisibility(View.GONE);
//                            if (getRefresher() != null)
//                                getRefresher().setRefreshing(false);
//                            onPostLoadAction();
//                        }
//                    }
//                });
//            }
//        };
//
//        final Action loadAction = new Action() {
//            @Override
//            public void execute() {
//                if(alreadyLoaded)
//                    //LocManager.getInstance().getLatLng();
//                    alreadyLoaded=true;
//                api = getNewApi().request(onSuccess, onFail);
//                onPreLoadAction();
//            }
//        };
//        return loadAction;
//    }
//    protected abstract SwipeRefreshLayout getRefresher();
//    public abstract PagingAdapter getNewPagingAdapter(T items);
//    public abstract PagingApi getNewApi();
//    protected abstract StickyListHeadersListView getListView();
//
//    public PagingAdapter<T> getAdapter() {
//        return adapter;
//    }
//
//    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
//        this.onScrollListener = onScrollListener;
//    }
//
//    public AbsListView.OnScrollListener getOnScrollListener() {
//        return onScrollListener;
//    }
//
//    public abstract void onPreLoadAction();
//    public abstract void onPostLoadAction();
//}
