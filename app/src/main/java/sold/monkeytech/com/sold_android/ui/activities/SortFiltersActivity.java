package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivitySortFiltersBinding;

public class SortFiltersActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySortFiltersBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sort_filters);
        initUi();
    }

    private void initUi() {
//        mBinding.sortActAZ.setOnClickListener(this);
//        mBinding.sortActZA.setOnClickListener(this);
//        mBinding.sortActPrice.setOnClickListener(this);
//        mBinding.sortActRooms.setOnClickListener(this);
//        mBinding.sortActSize.setOnClickListener(this);
//        mBinding.sortActTime.setOnClickListener(this);
//        mBinding.sortActPriceDown.setOnClickListener(this);
//        mBinding.sortActBackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        deselectAll();
        selectThis(view);
    }

    public void deselectAll(){
//        mBinding.sortActAZV.setVisibility(View.INVISIBLE);
//        mBinding.sortActZAV.setVisibility(View.INVISIBLE);
//        mBinding.sortActPriceV.setVisibility(View.INVISIBLE);
//        mBinding.sortActRoomsV.setVisibility(View.INVISIBLE);
//        mBinding.sortActSizeV.setVisibility(View.INVISIBLE);
//        mBinding.sortActTimeV.setVisibility(View.INVISIBLE);
//        mBinding.sortActPriceDownV.setVisibility(View.INVISIBLE);
    }

    public void selectThis(View view){
//        switch (view.getId()){
//            case R.id.sortActAZ:
//                mBinding.sortActAZV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActZA:
//                mBinding.sortActZAV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActPrice:
//                mBinding.sortActPriceV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActRooms:
//                mBinding.sortActRoomsV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActSize:
//                mBinding.sortActSizeV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActTime:
//                mBinding.sortActTimeV.setVisibility(View.VISIBLE);
//                break;
//            case R.id.sortActPriceDown:
//                mBinding.sortActPriceDownV.setVisibility(View.VISIBLE);
//                break;
//        }
    }
}

