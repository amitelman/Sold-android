package sold.monkeytech.com.sold_android.ui.fragments.preApprovedFragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentPreApproved4Binding;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreApproved4Fragment extends BaseFragment {


    private FragmentPreApproved4Binding mBinding;
    public On4Listener listener;
    private TextView inputView;
    private int seniority = 0;

    public PreApproved4Fragment() {
        // Required empty public constructor
    }

    public interface On4Listener{
        void onFrag4(int seniority, int monthlyIncome);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pre_approved4, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();
    }

    private void initUi() {
        inputView = mBinding.preApproved4ActText;
        seniority = mBinding.preApproved4ActSeekBar.getProgress();
        mBinding.preApproved4ActSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                p.addRule(RelativeLayout.BELOW, seekBar.getId());
                Rect thumbRect =  seekBar.getThumb().getBounds();
                Log.d("wowSeekBar","seek: " + thumbRect.centerX());
                if(thumbRect.centerX() < 700){
                    p.setMargins(thumbRect.centerX(),0, 0, 0);
                    inputView.setLayoutParams(p);
                    inputView.invalidate();
                }
                inputView.setText(String.valueOf(progress) + " years");
                seniority = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBinding.preApproved4ActNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthlyIncomeStr = mBinding.preApproved4ActIncome.getText().toString();
                if(!TextUtils.isEmpty(monthlyIncomeStr) && seniority != -1){
                    int monthlyIncome = Integer.parseInt(monthlyIncomeStr.replaceAll("\\D+",""));
                    listener.onFrag4(seniority, monthlyIncome);
                }else{
                    Toast.makeText(getContext(), "Please Set your monthly income", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        final String occupation = mBinding.preApproved4ActInput.getText().toString();
//        mBinding.preApproved4ActNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onFrag4(occupation);
//            }
//        });
    }
}
