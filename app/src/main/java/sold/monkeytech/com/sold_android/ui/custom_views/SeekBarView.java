package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.SeekBarViewBinding;
import sold.monkeytech.com.sold_android.databinding.ValuesRangeViewBinding;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.ui.custom_views.abs.RangeViewAbs;


/**
 * Created by MonkeyFather on 12/07/2017.
 */

public class SeekBarView extends LinearLayout {

    private SeekBarViewBinding mBinding;
    private TypedArray attr;
    private boolean isInputFocus = false;

    public SeekBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi(context, attrs);
    }

    public SeekBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context, attrs);
    }

    protected void initUi(Context context, AttributeSet attrs) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.seek_bar_view, this, true);
        attr = context.obtainStyledAttributes(attrs, R.styleable.SeekBarViewValues);

        String title = attr.getString(R.styleable.SeekBarViewValues_title);
        int max = attr.getInt(R.styleable.SeekBarViewValues_max, 10);
        boolean withInput = attr.getBoolean(R.styleable.SeekBarViewValues_withInput, true);
        final String inputType = attr.getString(R.styleable.SeekBarViewValues_inputType);
        final boolean isDecimal = attr.getBoolean(R.styleable.SeekBarViewValues_isDecimal, false);

        if(isDecimal){
            mBinding.seekBarViewInput.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        if(!TextUtils.isEmpty(title))
            mBinding.seekBarViewTitle.setText(title);

        if(withInput){
            mBinding.seekBarViewInput.setVisibility(VISIBLE);
            mBinding.seekBarViewInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("wowSeekView","onTextChange");
                    String input = s.toString().replaceAll("[^\\d.]", "");
                    if(isDecimal){
//                        mBinding.seekBarViewBar.setProgress(Integer.parseInt(input));
                    }else{
                        if(!TextUtils.isEmpty(input)){
                            mBinding.seekBarViewBar.setProgress(Integer.parseInt(input));
                            mBinding.seekBarViewInput.setSelection(mBinding.seekBarViewInput.getText().length());
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else{
            mBinding.seekBarViewInput.setVisibility(GONE);
        }

        mBinding.seekBarViewBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("wowSeekView","onProgressChanged");
                String seekProgress = String.valueOf(progress);
                if(isDecimal){
                    float currentProgress = progress * 0.1f;
                    seekProgress = String.format("%.1f", currentProgress);
                }
                mBinding.seekBarViewInput.setText(seekProgress + (isInputFocus ? "" : " " + inputType));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBinding.seekBarViewInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isInputFocus = hasFocus;
                String input = ((EditText) v).getText().toString();
                if(!TextUtils.isEmpty(input)){
                    if(hasFocus){
                        ((EditText) v).setText(input.replaceAll("[^\\d.]", ""));
                    }else{
                        ((EditText) v).setText(input + " " + inputType);
                    }
                }
            }
        });

        mBinding.seekBarViewBar.setMax(max);
        mBinding.seekBarViewBar.setProgress(max/2);

    }

}
