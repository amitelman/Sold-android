package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityFilterSearchBinding;
import sold.monkeytech.com.sold_android.framework.Utils.KeyboardAndInputUtils;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.ui.fontable_views.FontableTextView;

public class FilterSearchActivity extends Activity implements View.OnClickListener {

    private ActivityFilterSearchBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter_search);

        initUi();
    }

    private void initUi() {
        KeyboardAndInputUtils.closeKeyboard(this);
        initPropertyBtns();
        initExpandableLayout();
        initAddFeaturesBtns();
    }

    private void initPropertyBtns() {
        mBinding.filterActTypeApartmentMain.setOnClickListener(this);
        mBinding.filterActTypeGardenMain.setOnClickListener(this);
        mBinding.filterActTypePenthouseMain.setOnClickListener(this);
        mBinding.filterActTypeCottageMain.setOnClickListener(this);
        mBinding.filterActTypeMiniPenthouseMain.setOnClickListener(this);
        mBinding.filterActTypeDoubleMain.setOnClickListener(this);
        mBinding.filterActTypeVillaMain.setOnClickListener(this);
        mBinding.filterActTypeGroundMain.setOnClickListener(this);
    }

    private void initExpandableLayout() {
        mBinding.filterActAdditionalFeatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.filterActAdditionalLayout.getVisibility() == View.VISIBLE) {
                    MyAnimationUtils.rotateRight(mBinding.filterActAdditionalFeatureImg);
                    MyAnimationUtils.collapse(mBinding.filterActAdditionalLayout);
                } else {
                    MyAnimationUtils.rotateLeft(mBinding.filterActAdditionalFeatureImg);
                    MyAnimationUtils.expand(mBinding.filterActAdditionalLayout);
                }
            }
        });

        mBinding.filterActSaveSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.filterActSaveLayout.getVisibility() == View.VISIBLE) {
                    MyAnimationUtils.collapse(mBinding.filterActSaveLayout);
                } else {
                    MyAnimationUtils.expand(mBinding.filterActSaveLayout);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.filterActScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 500);
                }
            }
        });
    }

    private void initAddFeaturesBtns() {
        mBinding.filterActAdditionalFeatTama.setOnClickListener(this);
        mBinding.filterActAdditionalFeatPool.setOnClickListener(this);
        mBinding.filterActAdditionalFeatBasement.setOnClickListener(this);
        mBinding.filterActAdditionalFeatMedia.setOnClickListener(this);
        mBinding.filterActAdditionalFeatWarehouse.setOnClickListener(this);
        mBinding.filterActAdditionalFeatHousingUnit.setOnClickListener(this);
        mBinding.filterActAdditionalFeatElevator.setOnClickListener(this);
        mBinding.filterActAdditionalFeatMamad.setOnClickListener(this);
        mBinding.filterActAdditionalFeatParking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.filterActTypeApartmentMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeApartment);
                break;
            case R.id.filterActTypeGardenMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeGarden);
                break;
            case R.id.filterActTypePenthouseMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypePenthouse);
                break;
            case R.id.filterActTypeCottageMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeCottage);
                break;
            case R.id.filterActTypeMiniPenthouseMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeMiniPenthouse);
                break;
            case R.id.filterActTypeDoubleMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeDouble);
                break;
            case R.id.filterActTypeVillaMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeVilla);
                break;
            case R.id.filterActTypeGroundMain:
                setPropertyClickUi(view.isSelected(), view, mBinding.filterActTypeGround);
                break;
            case R.id.filterActAdditionalFeatTama:
            case R.id.filterActAdditionalFeatPool:
            case R.id.filterActAdditionalFeatBasement:
            case R.id.filterActAdditionalFeatMedia:
            case R.id.filterActAdditionalFeatWarehouse:
            case R.id.filterActAdditionalFeatHousingUnit:
            case R.id.filterActAdditionalFeatElevator:
            case R.id.filterActAdditionalFeatMamad:
            case R.id.filterActAdditionalFeatParking:
                setAdditionalFeaturesClickUi((TextView) view);

                break;
        }
    }

    private void setAdditionalFeaturesClickUi(TextView view) {
        boolean selected = view.isSelected();
        setFontType(!selected, view);
        view.setSelected(!selected);
    }

    private void setPropertyClickUi(boolean selected, View mainView, FontableTextView textView) {
        if(selected){
            mainView.setSelected(false);
            textView.setSelected(false);
        }else{
            mainView.setSelected(true);
            textView.setSelected(true);
        }
        setFontType(!selected, textView);
    }

    private void setFontType(boolean selected, TextView view){
        Typeface tf = null;
        if(selected)
            tf = Typeface.createFromAsset(getAssets(), "fonts/Muli-Bold.ttf");
        else
            tf = Typeface.createFromAsset(getAssets(), "fonts/Muli-Regular.ttf");
        view.setTypeface(tf);
    }
}

