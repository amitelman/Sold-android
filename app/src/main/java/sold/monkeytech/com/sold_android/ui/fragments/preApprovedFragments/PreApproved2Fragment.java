package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved1Binding;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved2Binding;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved2Fragment extends BaseFragment {


    private FragmentPreApproved2Binding mBinding;
    public On2Listener listener;
    private int loanAmount = 0;
    private int propertyValue = 0;

    public PreApproved2Fragment() {
        // Required empty public constructor
    }

    public BaseFragment setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
        return this;
    }

    public interface On2Listener{
        void onFrag2(int loanAmout, int equity);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved2, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initUi();
    }

    private void initUi() {
        mBinding.preApproved2ActSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBinding.preApproved2ActAmount.setText(progress + "");
                loanAmount = progress;
                mBinding.preApproved2ActEquity.setText(propertyValue - progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBinding.preApproved2ActAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                if(!TextUtils.isEmpty(input)){
                    mBinding.preApproved2ActSeekBar.setProgress(Integer.parseInt(input));
                    mBinding.preApproved2ActAmount.setSelection(mBinding.preApproved2ActAmount.getText().length());
                    loanAmount = Integer.parseInt(input);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.preApproved2ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo : is there minimum loan amount ?
                String equityStr = mBinding.preApproved2ActEquity.getText().toString();
                if(!TextUtils.isEmpty(equityStr)){
                    int equity = Integer.parseInt(equityStr.replaceAll("\\D+",""));
                    listener.onFrag2(loanAmount, equity);
                }else{
                    Toast.makeText(getContext(), "Please Set your loan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
