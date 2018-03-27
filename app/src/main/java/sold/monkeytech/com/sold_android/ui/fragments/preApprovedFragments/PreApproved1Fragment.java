package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved1Binding;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApprovedStartBinding;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved1Fragment extends BaseFragment {


    private static final int FIRST_APT = 0;
    private static final int BUY_SELL = 1;
    private static final int INVESTMENT = 2;

    private FragmentPreApproved1Binding mBinding;
    public On1Listener listener;
    private Integer selected;

    public PreApproved1Fragment() {
        // Required empty public constructor
    }

    public interface On1Listener{
        void onFrag1(int type);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved1, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {

        mBinding.preApproved1ActFirstHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                unCheckAll();
                buttonView.setChecked(isChecked);
                selected = FIRST_APT;
            }});

        mBinding.preApproved1ActSellToBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                unCheckAll();
                buttonView.setChecked(isChecked);
                selected = BUY_SELL;
            }});

        mBinding.preApproved1ActInvestment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                unCheckAll();
                buttonView.setChecked(isChecked);
                selected = INVESTMENT;
            }});

        mBinding.preApproved1ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected != null)
                    listener.onFrag1(selected);
                else{
                    //todo: warning?
                    Toast.makeText(getContext(), "Please choose an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void unCheckAll(){
        mBinding.preApproved1ActFirstHome.setChecked(false);
        mBinding.preApproved1ActSellToBuy.setChecked(false);
        mBinding.preApproved1ActInvestment.setChecked(false);
    }
}
