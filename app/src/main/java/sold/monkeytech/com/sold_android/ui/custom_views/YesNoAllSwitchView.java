//package sold.monkeytech.com.sold_android.ui.custom_views;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.databinding.DataBindingUtil;
//import android.graphics.Typeface;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.mid.mid.R;
//import com.mid.mid.databinding.YesNoAllSwitchViewBinding;
//import com.mid.mid.framework.Utils.KeyboardAndTextUtils;
//import com.mid.mid.framework.Utils.TextUtils;
//
///**
// * Created by MonkeyFather on 12/07/2017.
// */
//
//public class YesNoAllSwitchView extends LinearLayout implements View.OnClickListener {
//
//    private static final int YES_VIEW = 0;
//    private static final int NO_VIEW = 1;
//    private static final int ALL_VIEW = 2;
//    private static final int RED = -1690862;
//    private static final int YELLOW = -2701824;
//    private static final int ORANGE = -949504;
//    private YesNoAllSwitchViewBinding mBinding;
//    private int yesColor;
//    private int noColor;
//    private int selectedValue = -1;
//    private int extensionSelectedValue = -1;
//    private boolean isWithExtension = false;
//    private boolean isPerfectMatch = false;
//    private boolean hideAllBtn = false;
//    private boolean isSeperable = false;
//    private Boolean exdtensionSelected;
//    private int EXT_PERFECT_MATCH = 3;
//    private int EXT_SEPRABLE = 4;
//    private int EXT_ALL = 5;
//    private int mDefault;
//
//    public YesNoAllSwitchView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initUi(context, attrs);
//    }
//
//
//    public YesNoAllSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initUi(context, attrs);
//    }
//
//    private void initUi(Context context, AttributeSet attrs) {
//        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.yes_no_all_switch_view, this, true);
//
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YesNoAllSwitchViewValues);
//
//        //set title
//        String title = a.getString(R.styleable.YesNoAllSwitchViewValues_title);
//        if(title != null)
//            mBinding.yesNoAllSwitchViewTitle.setText(title);
//        else
//            mBinding.yesNoAllSwitchViewTitle.setVisibility(GONE);
//
//
//        yesColor = a.getInt(R.styleable.YesNoAllSwitchViewValues_yesColor,0);
//        noColor = a.getInt(R.styleable.YesNoAllSwitchViewValues_noColor,0);
//        mBinding.yesNoAllSwitchViewYes.setOnClickListener(this);
//        mBinding.yesNoAllSwitchViewNo.setOnClickListener(this);
//        mBinding.yesNoAllSwitchViewAll.setOnClickListener(this);
//
//        mBinding.yesNoAllSwitchViewCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mBinding.yesNoAllSwitchViewTitle.setTextColor(getResources().getColor(R.color.orange));
//                mBinding.yesNoAllSwitchViewCancel.setVisibility(VISIBLE);
//                deselect();
//            }
//        });
//
//        isWithExtension = a.getBoolean(R.styleable.YesNoAllSwitchViewValues_withExtension, false);
//        if(isWithExtension){
//            mBinding.yesNoAllSwitchViewExtensionLayout.setVisibility(VISIBLE);
//            mBinding.yesNoAllSwitchViewPrefectMatch.setOnClickListener(this);
//            mBinding.yesNoAllSwitchViewSeperable.setOnClickListener(this);
//            mBinding.yesNoAllSwitchViewExtensionAll.setOnClickListener(this);
//        }
//
//        hideAllBtn = a.getBoolean(R.styleable.YesNoAllSwitchViewValues_hideAllBtn, false);
//        if(hideAllBtn){
//            mBinding.yesNoAllSwitchViewAll.setVisibility(GONE);
//            mBinding.yesNoAllSwitchViewAllSep.setVisibility(GONE);
//            mBinding.yesNoAllSwitchViewNo.setBackgroundResource(R.drawable.shape_grey_small_box_right_radius);
//        }
//
//        //set default
//        mDefault = a.getInt(R.styleable.YesNoAllSwitchViewValues_mDefault, ALL_VIEW);
//        switch (mDefault){
//            case YES_VIEW:
//                mBinding.yesNoAllSwitchViewYes.performClick();
//                break;
//            case NO_VIEW:
//                mBinding.yesNoAllSwitchViewNo.performClick();
//                break;
//            case ALL_VIEW:
//                mBinding.yesNoAllSwitchViewAll.performClick();
//                break;
//            case 22:
//                mBinding.yesNoAllSwitchViewExtensionAll.performClick();
//                break;
//        }
//
//        String noText = a.getString(R.styleable.YesNoAllSwitchViewValues_noText);
//        String yesText = a.getString(R.styleable.YesNoAllSwitchViewValues_yesText);
//        if(!TextUtils.isEmpty(noText))
//            mBinding.yesNoAllSwitchViewNo.setText(noText);
//        if(!TextUtils.isEmpty(yesText))
//            mBinding.yesNoAllSwitchViewYes.setText(yesText);
//    }
//
//
//
//    private void resetSelection(){
//        Typeface face = Typeface.createFromAsset(getContext().getAssets(),"fonts/Lato-Regular.ttf");
//        mBinding.yesNoAllSwitchViewYes.setTypeface(face);
//        mBinding.yesNoAllSwitchViewNo.setTypeface(face);
//        mBinding.yesNoAllSwitchViewAll.setTypeface(face);
//
//        mBinding.yesNoAllSwitchViewYes.setBackgroundResource(R.drawable.shape_grey_small_box_left_radius);
//        mBinding.yesNoAllSwitchViewYes.setTextColor(getResources().getColor(R.color.black));
//        mBinding.yesNoAllSwitchViewNo.setTextColor(getResources().getColor(R.color.black));
//        if(hideAllBtn){
//            mBinding.yesNoAllSwitchViewNo.setBackgroundResource(R.drawable.shape_grey_small_box_right_radius);
//        }else{
//            mBinding.yesNoAllSwitchViewNo.setBackgroundResource(R.drawable.shape_grey_small_box_no_radius);
//        }
//        mBinding.yesNoAllSwitchViewAll.setBackgroundResource(R.drawable.shape_grey_small_box_right_radius);
//        mBinding.yesNoAllSwitchViewAll.setTextColor(getResources().getColor(R.color.black));
//
//    }
//
//    private void resetExtensionSelection(){
//        Typeface face = Typeface.createFromAsset(getContext().getAssets(),"fonts/Lato-Regular.ttf");
//        mBinding.yesNoAllSwitchViewPrefectMatch.setTypeface(face);
//        mBinding.yesNoAllSwitchViewSeperable.setTypeface(face);
//        mBinding.yesNoAllSwitchViewExtensionAll.setTypeface(face);
//
//        mBinding.yesNoAllSwitchViewPrefectMatch.setBackgroundResource(R.drawable.shape_grey_small_box_left_radius);
//        mBinding.yesNoAllSwitchViewPrefectMatch.setTextColor(getResources().getColor(R.color.black));
//        mBinding.yesNoAllSwitchViewSeperable.setBackgroundResource(R.drawable.shape_grey_small_box_no_radius);
//        mBinding.yesNoAllSwitchViewSeperable.setTextColor(getResources().getColor(R.color.black));
//        mBinding.yesNoAllSwitchViewExtensionAll.setBackgroundResource(R.drawable.shape_grey_small_box_right_radius);
//        mBinding.yesNoAllSwitchViewExtensionAll.setTextColor(getResources().getColor(R.color.black));
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.yesNoAllSwitchViewYes:
//
//                resetSelection();
//                setSelectedView(YES_VIEW);
//                selectedValue = YES_VIEW;
//                showCancelBtn();
//
////                if(isWithExtension){
////                    mBinding.yesNoAllSwitchViewExtensionLayout.setAlpha(1);
////                    mBinding.yesNoAllSwitchViewExtensionLayout.setClickable(true);
////
////                }
//                break;
//            case R.id.yesNoAllSwitchViewNo:
//                resetSelection();
//                setSelectedView(NO_VIEW);
//                selectedValue = NO_VIEW;
//                showCancelBtn();
////                if(isWithExtension){
////                    hideExtension();
////                }
//                break;
//            case R.id.yesNoAllSwitchViewAll:
//                resetSelection();
//                setSelectedView(ALL_VIEW);
//                selectedValue = ALL_VIEW;
//                hideCancelBtn();
////                if(mDefault != ALL_VIEW)
////                    showCancelBtn();
////                if(isWithExtension){
////                    hideExtension();
////                }
//                break;
//
//            case R.id.yesNoAllSwitchViewPrefectMatch:
//                resetExtensionSelection();
//                setSelectedView(EXT_PERFECT_MATCH);
//                extensionSelectedValue = EXT_PERFECT_MATCH;
//                mBinding.yesNoAllSwitchViewYes.performClick();
//                break;
//
//            case R.id.yesNoAllSwitchViewSeperable:
//                resetExtensionSelection();
//                setSelectedView(EXT_SEPRABLE);
//                extensionSelectedValue = EXT_SEPRABLE;
//                mBinding.yesNoAllSwitchViewYes.performClick();
//                break;
//            case R.id.yesNoAllSwitchViewExtensionAll:
//                resetExtensionSelection();
//                setSelectedView(EXT_ALL);
//                extensionSelectedValue = EXT_ALL;
//                break;
//        }
//    }
//
//    private void hideExtension() {
//        mBinding.yesNoAllSwitchViewExtensionLayout.setAlpha(0.3f);
//        mBinding.yesNoAllSwitchViewExtensionLayout.setClickable(false);
//        mBinding.yesNoAllSwitchViewSeperable.setClickable(false);
//        mBinding.yesNoAllSwitchViewPrefectMatch.setClickable(false);
//        setSeperable(false);
//        setPerfectMatch(false);
//        KeyboardAndTextUtils.getUnSelectedUiTextView(mBinding.yesNoAllSwitchViewSeperable);
//        KeyboardAndTextUtils.getUnSelectedUiTextView(mBinding.yesNoAllSwitchViewPrefectMatch);
//    }
//
//    private void setSelectedView(int view) {
//        if(view == YES_VIEW || view == EXT_PERFECT_MATCH){
//            View v = view == YES_VIEW ? mBinding.yesNoAllSwitchViewYes : mBinding.yesNoAllSwitchViewPrefectMatch;
//            if(yesColor == 0)
//                v.setBackgroundResource(R.drawable.shape_turquise_small_box_left_radius);
//            if(yesColor == RED)
//                v.setBackgroundResource(R.drawable.shape_red_small_box_left_radius);
//            if(yesColor == YELLOW)
//                v.setBackgroundResource(R.drawable.shape_yellow_small_box_left_radius);
//            if(yesColor == ORANGE)
//                v.setBackgroundResource(R.drawable.shape_orange_small_box_left_radius);
//
//            KeyboardAndTextUtils.getCustomTextView((TextView) v, KeyboardAndTextUtils.WHITE, KeyboardAndTextUtils.BLACK, 0);
//        }
//        else if(view == NO_VIEW || view == EXT_SEPRABLE){
//            View v = view == NO_VIEW ? mBinding.yesNoAllSwitchViewNo : mBinding.yesNoAllSwitchViewSeperable;
//            if(hideAllBtn && view == NO_VIEW){
//                v.setBackgroundResource(R.drawable.shape_turquise_small_box_right_radius);
//            }else{
//                v.setBackgroundResource(R.drawable.shape_turquise_small_box_no_radius);
//                if(noColor == 0)
//                    v.setBackgroundResource(R.drawable.shape_turquise_small_box_no_radius);
//                if(noColor == RED)
//                    v.setBackgroundResource(R.drawable.shape_red_small_box_no_radius);
//                if(noColor == YELLOW)
//                    v.setBackgroundResource(R.drawable.shape_yellow_small_box_no_radius);
//                if(noColor == ORANGE)
//                    v.setBackgroundResource(R.drawable.shape_orange_small_box_no_radius);
//            }
//
//            KeyboardAndTextUtils.getCustomTextView((TextView) v, KeyboardAndTextUtils.WHITE, KeyboardAndTextUtils.BLACK, 0);
//        }
//        else if(view == ALL_VIEW || view == EXT_ALL){
//            View v = view == ALL_VIEW ? mBinding.yesNoAllSwitchViewAll : mBinding.yesNoAllSwitchViewExtensionAll;
//            v.setBackgroundResource(R.drawable.shape_turquise_small_box_right_radius);
//            KeyboardAndTextUtils.getCustomTextView((TextView) v, KeyboardAndTextUtils.WHITE, KeyboardAndTextUtils.BLACK, 0);
//        }
//    }
//
////    private void setSelectedView(View view) {
////            if(yesColor == 0)
////                view.setBackgroundResource(R.drawable.shape_turquise_small_box_left_radius);
////            if(yesColor == RED)
////                view.setBackgroundResource(R.drawable.shape_red_small_box_left_radius);
////            if(yesColor == YELLOW)
////                view.setBackgroundResource(R.drawable.shape_yellow_small_box_left_radius);
////            if(yesColor == ORANGE)
////                view.setBackgroundResource(R.drawable.shape_orange_small_box_left_radius);
////
////            KeyboardAndTextUtils.getCustomTextView((TextView) view, KeyboardAndTextUtils.WHITE, KeyboardAndTextUtils.BLACK, 0);
////    }
//
//    public Boolean getSelected() {
//        if(selectedValue == 0)
//            return true;
//        if(selectedValue == 1)
//            return false;
//        if(selectedValue == 2)
//            return null;
//        return null;
//    }
//
//    public Boolean getReversedSelected() {
//        if(selectedValue == 0)
//            return false;
//        if(selectedValue == 1)
//            return true;
//        if(selectedValue == 2)
//            return null;
//        return null;
//    }
//
//    public Boolean getExtensionSelected() {
//        if(extensionSelectedValue == 3) //Perfect match
//            return false;
//        if(extensionSelectedValue == 4) //Separable
//            return true;
//        if(extensionSelectedValue == 5) //All
//            return null;
//        return null;
//    }
//
//    public void showCancelBtn(){
//        mBinding.yesNoAllSwitchViewTitle.setTextColor(getResources().getColor(R.color.orange));
//        mBinding.yesNoAllSwitchViewCancel.setVisibility(VISIBLE);
//    }
//    public void hideCancelBtn(){
//        mBinding.yesNoAllSwitchViewTitle.setTextColor(getResources().getColor(R.color.black));
//        mBinding.yesNoAllSwitchViewCancel.setVisibility(GONE);
//    }
//
//
//
//    public void deselect() {
//        mBinding.yesNoAllSwitchViewTitle.setTextColor(getResources().getColor(R.color.black));
//        mBinding.yesNoAllSwitchViewCancel.setVisibility(GONE);
//        selectedValue = -1;
//        resetSelection();
//        if(isWithExtension){
//            resetExtensionSelection();
//        }
//        switch (mDefault){
//            case YES_VIEW:
//                mBinding.yesNoAllSwitchViewYes.performClick();
//                break;
//            case NO_VIEW:
//                mBinding.yesNoAllSwitchViewNo.performClick();
//                break;
//            case ALL_VIEW:
//                mBinding.yesNoAllSwitchViewAll.performClick();
//                break;
//            case 22:
//                mBinding.yesNoAllSwitchViewExtensionAll.performClick();
//                break;
//        }
//    }
//
//    public boolean isPerfectMatch() {
//        return isPerfectMatch;
//    }
//
//    public void setPerfectMatch(boolean perfectMatch) {
//        isPerfectMatch = perfectMatch;
//    }
//
//    public boolean isSeperable() {
//        return isSeperable;
//    }
//
//    public void setSeperable(boolean seperable) {
//        isSeperable = seperable;
//    }
//
//}
