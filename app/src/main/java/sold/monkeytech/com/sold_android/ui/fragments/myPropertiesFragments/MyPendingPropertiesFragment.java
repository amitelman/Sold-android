package sold.monkeytech.com.sold_android.ui.fragments.myPropertiesFragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentMyPendingPropertiesBinding;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.ui.adapters.PendingPropertyAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPendingPropertiesFragment extends BaseFragment {


    private FragmentMyPendingPropertiesBinding mBinding;
    private List<Property> properties;
    private PendingPropertyAdapter adapter;

    public MyPendingPropertiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_pending_properties, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    public BaseFragment setProperties(List<Property> pendingProperties) {
        this.properties = pendingProperties;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        adapter = new PendingPropertyAdapter(getContext(), properties);
        mBinding.myPendingPropFragListView.setAdapter(adapter);
    }
}
