package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ClickValuesRangeViewBinding;
import sold.monkeytech.com.sold_android.databinding.ValuesRangeViewBinding;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.ui.custom_views.abs.RangeViewAbs;


/**
 * Created by MonkeyFather on 12/07/2017.
 */

public class ClickValuesRangeView extends LinearLayout {

    private static final int MINUS = 0;
    private static final int PLUS = 1;

    private ClickValuesRangeViewBinding mBinding;
    private TypedArray attr;
    private int counter = 0;

    public ClickValuesRangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi(context, attrs);
    }

    public ClickValuesRangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context, attrs);
    }

    protected void initUi(Context context, AttributeSet attrs) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.click_values_range_view, this, true);
        attr = context.obtainStyledAttributes(attrs, R.styleable.ValuesRangeViewValues);
        String title = attr.getString(R.styleable.ValuesRangeViewValues_title);
        if(title != null){
            mBinding.clickValuesRangeViewTitle.setText(title);
        }

        mBinding.clickValuesRangeViewMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calcValue(MINUS);
            }
        });
        mBinding.clickValuesRangeViewPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calcValue(PLUS);
            }
        });

        mBinding.clickValuesRangeViewInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mBinding.clickValuesRangeViewInput.setEnabled(false);
                    return true;
                }
                return false;
            }
        });


    }

    private void calcValue(int type) {
        String value = mBinding.clickValuesRangeViewInput.getText().toString().replaceAll("[^\\d.]", "");
        counter = TextUtils.isEmpty(value) ? 0 : Integer.parseInt(value);
        if(counter >= 0){
            if(counter > 0 && type == MINUS)
                counter--;
            if(type == PLUS)
                counter++;
            mBinding.clickValuesRangeViewInput.setText(counter + "");
        }
    }

    public void clearClickRange(){
        mBinding.clickValuesRangeViewInput.setText("");
    }

    public int getCounter(){
        return counter;
    }





}
