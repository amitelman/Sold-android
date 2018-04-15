package sold.monkeytech.com.sold_android.ui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSellBinding;
import sold.monkeytech.com.sold_android.ui.activities.SearchLocationActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends BaseFragment implements View.OnClickListener {


    private static final int CHOOSE_CITY = 0;
    private static final int CHOOSE_STREET = 1;
    private FragmentSellBinding mBinding;
    private String cityName = "";
    private int cityId = -1;
    private String streetName;
    private int streetId = -1;

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
        initUi();

    }

    private void initUi() {
        mBinding.sellFragCity.setOnClickListener(this);
        mBinding.sellFragStreet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sellFragCity:
                Intent intent = new Intent(getContext(), SearchLocationActivity.class);
                intent.putExtra("type", CHOOSE_CITY);
                startActivityForResult(intent, CHOOSE_CITY);
                break;
            case R.id.sellFragStreet:
                if(TextUtils.isEmpty(cityName)){
                    Toast.makeText(getContext(), "Please choose your city first", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent2 = new Intent(getContext(), SearchLocationActivity.class);
                    intent2.putExtra("type", CHOOSE_STREET);
                    intent2.putExtra("cityId", cityId);
                    startActivityForResult(intent2, CHOOSE_STREET);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CHOOSE_CITY){
                cityName = data.getStringExtra("cityName");
                cityId = (int) data.getLongExtra("cityId", -1);
                mBinding.sellFragCity.setText(cityName);
            }else{
                streetName = data.getStringExtra("streetName");
                streetId = (int) data.getLongExtra("streetId", -1);
                mBinding.sellFragStreet.setText(streetName);
            }
        }
    }
}
