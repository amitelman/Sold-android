package sold.monkeytech.com.sold_android.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentServiceItemBinding;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceItemFragment extends BaseFragment {

    private ServicePage servicePage;
    private FragmentServiceItemBinding mBinding;

    public ServiceItemFragment() {
        // Required empty public constructor
    }

    public ServiceItemFragment setPage(ServicePage servicePage){
        this.servicePage = servicePage;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_item, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        if(servicePage != null){
            mBinding.serviceFragItemTitle.setText(servicePage.getTitle());
            mBinding.serviceFragItemContent.setText(servicePage.getContent());
            ImageLoaderUtils.loadBigPictureImage(servicePage.getImage(), mBinding.serviceFragItemImg, null);
        }
    }

}
