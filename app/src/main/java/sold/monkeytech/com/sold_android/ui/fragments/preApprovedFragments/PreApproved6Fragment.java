package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved6Binding;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved6Fragment extends BaseFragment {


    private FragmentPreApproved6Binding mBinding;
    public On6Listener listener;

    public PreApproved6Fragment() {
        // Required empty public constructor
    }

    public interface On6Listener{
        void onFrag6(int disposableIncome, String otherIncome);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved6, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {

        mBinding.preApproved8ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otherIncome = mBinding.preApproved6ActOtherInput.getText().toString();
                String disposableIncomeStr = mBinding.preApproved6ActInput.getText().toString();
                if (!TextUtils.isEmpty(disposableIncomeStr)) {
                    final int disposableIncome = Integer.parseInt(disposableIncomeStr);
                    listener.onFrag6(disposableIncome, otherIncome);
                } else {
                    Toast.makeText(getContext(), "Please Set your income", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
