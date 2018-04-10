package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ValuesRangeViewBinding;
import sold.monkeytech.com.sold_android.ui.custom_views.abs.RangeViewAbs;


/**
 * Created by MonkeyFather on 12/07/2017.
 */

public class ValuesRangeView extends RangeViewAbs {

    private ValuesRangeViewBinding mBinding;
    private TypedArray attr;

    public ValuesRangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ValuesRangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initUi(Context context, AttributeSet attrs) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.values_range_view, this, true);
        attr = context.obtainStyledAttributes(attrs, R.styleable.ValuesRangeViewValues);

        getFromEditText().setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do here your stuff f
                    getFromEditText().setEnabled(false);
                    getFromEditText().setEnabled(true);
                    return true;
                }
                return false;
            }

        });
    }

    public void clearRanges(){
        getFromEditText().setText("");
        getToEditText().setText("");
    }

    public String getRangeValues(){
        String values = mBinding.valuesRangeViewFrom.getText().toString() + " - " + mBinding.valuesRangeViewTo.getText().toString();
        return values;
    }

    @Override
    public TypedArray getAttrs() {
        return attr;
    }

    @Override
    protected int getAttrsTitleId() {
        return R.styleable.ValuesRangeViewValues_title;
    }

//    @Override
//    protected int getAttrsToId() {
//        return R.styleable.ValuesRangeViewValues_to;
//    }
//
//    @Override
//    protected int getAttrsFromId() {
//        return R.styleable.ValuesRangeViewValues_from;
//    }

    @Override
    public TextView getTitleView() {
        return mBinding.valuesRangeViewTitle;
    }

    @Override
    public EditText getFromEditText() {
        return mBinding.valuesRangeViewFrom;
    }

    @Override
    public EditText getToEditText() {
        return mBinding.valuesRangeViewTo;
    }

    public int getMinimum(){
        String input = getFromEditText().getText().toString();
        if(!TextUtils.isEmpty(input))
            return Integer.parseInt(getFromEditText().getText().toString());
        return 0;
    }

    public int getMaximum(){
        String input = getToEditText().getText().toString();
        if(!TextUtils.isEmpty(input))
            return Integer.parseInt(getFromEditText().getText().toString());
        return 0;
    }
//    @Override
//    public ImageButton getCancelBtn() {
////        return mBinding.valuesRangeViewCancel;
//        return null;
//    }

}
