package sold.monkeytech.com.sold_android.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSellBinding;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends BaseFragment {


    private FragmentSellBinding mBinding;

    public SellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false);
        View view = mBinding.getRoot();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mBinding.sellFragPb.showWithScroll(mBinding.sellFragScrollView);

    }
}
