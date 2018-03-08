//package sold.monkeytech.com.sold_android.ui.custom_views;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.databinding.DataBindingUtil;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.mid.mid.R;
//import com.mid.mid.databinding.MultiValuesRangeViewBinding;
//import com.mid.mid.framework.Utils.KeyboardAndTextUtils;
//import com.mid.mid.framework.managers.DataFiltersManager;
//import com.mid.mid.ui.custom_views.abs.RangeViewAbs;
//
///**
// * Created by MonkeyFather on 12/07/2017.
// */
//
//public class MultiValuesRangeView extends RangeViewAbs {
//
//    private MultiValuesRangeViewBinding mBinding;
//    public final int TOTAL = 0;
//    public final int PPC = 1;
//    public final int RAP = 2;
//    public int type = 0;
//
//
//    public MultiValuesRangeView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MultiValuesRangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    protected void initUi(Context context, AttributeSet attrs) {
//        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.multi_values_range_view, this, true);
//
//        mBinding.multiValueRangeTotal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeTotal, KeyboardAndInputUtils.TURQUISE, KeyboardAndInputUtils.BOLD, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangePPC, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeRap, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                clearAllValues();
//                setRange(DataFiltersManager.getInstance().getMinTotalPrice(), DataFiltersManager.getInstance().getMaxTotalPrice(), "$");
//                setType(TOTAL);
//            }
//        });
//        mBinding.multiValueRangePPC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangePPC, KeyboardAndInputUtils.TURQUISE, KeyboardAndInputUtils.BOLD, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeTotal, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeRap, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                clearAllValues();
//                setRange(DataFiltersManager.getInstance().getMinPricePerCarat(), DataFiltersManager.getInstance().getMaxPricePerCarat(), "$");
//                setType(PPC);
//            }
//        });
//        mBinding.multiValueRangeRap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeRap, KeyboardAndInputUtils.TURQUISE, KeyboardAndInputUtils.BOLD, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangeTotal, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                KeyboardAndInputUtils.getCustomTextView(mBinding.multiValueRangePPC, KeyboardAndInputUtils.BLACK, KeyboardAndInputUtils.REG, 0);
//                clearAllValues();
//                setRange(DataFiltersManager.getInstance().getMinDiscount(), DataFiltersManager.getInstance().getMaxDiscount(), "%");
//                setType(RAP);
//            }
//        });
//        mBinding.multiValueRangeTotal.performClick();
//
//    }
//
//    private void clearAllValues() {
//        mBinding.multiValuesRangeViewFrom.setText("");
//        mBinding.multiValuesRangeViewTo.setText("");
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
////    public int getFromValue(){
////        String val = mBinding.multiValuesRangeViewFrom.getText().toString();
////        if(val.contains("$"))
////            val.replace("$","");
////        if(val.contains("%"))
////            val.replace("%","");
////        return Integer.parseInt(val);
////    }
//
////    public int getToValue(){
////        String val = mBinding.multiValuesRangeViewTo.getText().toString();
////        if(val.contains("$"))
////            val.replace("$","");
////        if(val.contains("%"))
////            val.replace("%","");
////        return Integer.parseInt(val);
////    }
//
//    public String getRangeValues(){
//        String values = mBinding.multiValuesRangeViewFrom.getText().toString() + " - " + mBinding.multiValuesRangeViewTo.getText().toString();
//        return values;
//    }
//
//    @Override
//    protected TypedArray getAttrs() {
//        return null;
//    }
//
//    @Override
//    protected int getAttrsTitleId() {
//        return 0;
//    }
//
//    @Override
//    protected int getAttrsToId() {
//        return 0;
//    }
//
//    @Override
//    protected int getAttrsFromId() {
//        return 0;
//    }
//
//    @Override
//    protected TextView getTitleView() {
//        return mBinding.multiValuesRangeViewTitle;
//    }
//
//    @Override
//    protected ImageButton getCancelBtn() {
//        return mBinding.multiValuesRangeViewCancel;
//    }
//
//    @Override
//    public EditText getFromEditText() {
//        return mBinding.multiValuesRangeViewFrom;
//    }
//
//    @Override
//    public EditText getToEditText() {
//        return mBinding.multiValuesRangeViewTo;
//    }
//
//    @Override
//    public void deselect(){
//        super.deselect();
//        mBinding.multiValueRangeTotal.performClick();
//    }
//}
