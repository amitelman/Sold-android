package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

        getFromEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String toStr = getToEditText().getText().toString();
                if(toStr.length() > 0 && s.toString().length() > 0){
                    int from = Integer.parseInt(s.toString());
                    int to = Integer.parseInt(toStr);
                    if(from > to){
                        getFromEditText().setText(to + "");
                        getFromEditText().setSelection(getFromEditText().getText().length());
                    }
                }
            }
        });

        getToEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String fromStr = getFromEditText().getText().toString();
                if(fromStr.length() > 0 && s.toString().length() > 0){
                    int to = Integer.parseInt(s.toString());
                    int from = Integer.parseInt(fromStr);
                    if(to < from){
                        getToEditText().setText(from + "");
                        getToEditText().setSelection(getToEditText().getText().length());
                    }

                }
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
            return Integer.parseInt(getToEditText().getText().toString());
        return 0;
    }

    public void setMinimum(String minimum){
        getFromEditText().setText(minimum + "");
    }

    public void setMaximum(String maximum){
        getToEditText().setText(maximum + "");
    }
}
