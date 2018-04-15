package sold.monkeytech.com.sold_android.ui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSeachInListBinding;
import sold.monkeytech.com.sold_android.databinding.FragmentSellBinding;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetProperties;
import sold.monkeytech.com.sold_android.pagination.utils.ListViewReloader;
import sold.monkeytech.com.sold_android.ui.activities.SortFiltersActivity;
import sold.monkeytech.com.sold_android.ui.adapters.SearchInListAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

import static sold.monkeytech.com.sold_android.SoldApplication.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchInListFragment extends BaseFragment {


    private FragmentSeachInListBinding mBinding;
    private SearchInListAdapter adapter;

    private ListViewReloader reloader;

    public SearchInListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_seach_in_list, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
//        adapter = new SearchInListAdapter(getContext(), null);
//        mBinding.searchInListListView.setAdapter(adapter);

        mBinding.refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.refresher.setRefreshing(false);
                mBinding.refresher.setEnabled(false);

            }
        });

        reloader = new ListViewReloader<Integer,List<Property>,String>(mBinding.searchInListListView, adapter = new SearchInListAdapter(getContext(), null),
                new ApiGetProperties(getContext())){
            @Override
            public Integer getNextPage() {
                Log.d("wowInList","getNextPage()");
                return 1;
            }
        };
        Log.d("wowInList","initList");
        reloader.init(0);
        reloader.reloadList();
    }

    public void refreshSearch(List<Property> properties){
//        SearchParamManager.getInstance().updateParams("lat", latLng.latitude);
//        SearchParamManager.getInstance().updateParams("lng", latLng.longitude);
        Log.d("wowInList","refreshSearch");
        adapter.clearList();
        reloader.init(0);
        reloader.reloadList();
    }
}
