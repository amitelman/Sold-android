package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved1Binding;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved3Binding;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved3Fragment extends BaseFragment {


    private static final int SELF_EMPLOYED = 0;
    private static final int EMPLOYEE = 1;

    private FragmentPreApproved3Binding mBinding;
    public On3Listener listener;
    private int employmentStatus = 0;

    public PreApproved3Fragment() {
        // Required empty public constructor
    }

    public interface On3Listener{
        void onFrag3(int employmentStatus);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof PreApprovedActivity) {
            listener = (PreApprovedActivity) context;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved3, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        mBinding.preApproved3ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFrag3(employmentStatus);
            }
        });

        mBinding.preApproved3ActSelfEmployed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                unCheckAll();
                buttonView.setChecked(isChecked);
                employmentStatus = SELF_EMPLOYED;
            }
        });

        mBinding.preApproved3ActEmployee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                unCheckAll();
                buttonView.setChecked(isChecked);
                employmentStatus = EMPLOYEE;
            }
        });
    }

    private void unCheckAll() {
        mBinding.preApproved3ActSelfEmployed.setChecked(false);
        mBinding.preApproved3ActEmployee.setChecked(false);
    }

}
