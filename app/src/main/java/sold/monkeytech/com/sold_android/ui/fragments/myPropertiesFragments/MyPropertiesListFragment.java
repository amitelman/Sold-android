package sold.monkeytech.com.sold_android.ui.fragments.myPropertiesFragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentMyPropertiesListBinding;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.ui.adapters.SearchInListAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPropertiesListFragment extends BaseFragment {


    private static final int MY_PROPERTY = 1;
    private FragmentMyPropertiesListBinding mBinding;
    private SearchInListAdapter adapter;
    private List<Property> properties;


    public MyPropertiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_properties_list, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    public MyPropertiesListFragment setProperties(List<Property> approvedProperties) {
        this.properties = approvedProperties;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.pending_properties_header, mBinding.myPropListFragListView, false);
        if(mBinding.myPropListFragListView.getHeaderViewsCount() == 0)
            mBinding.myPropListFragListView.addHeaderView(header, null, false);

        adapter = new SearchInListAdapter(getContext(), properties);
        adapter.setAdapterType(MY_PROPERTY);
        mBinding.myPropListFragListView.setAdapter(adapter);
    }
}
