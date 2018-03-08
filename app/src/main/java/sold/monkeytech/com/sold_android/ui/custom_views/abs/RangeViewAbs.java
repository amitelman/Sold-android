package sold.monkeytech.com.sold_android.ui.custom_views.abs;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;

/**
 * Created by MonkeyFather on 13/07/2017.
 */

public abstract class RangeViewAbs extends LinearLayout{

    private static final int FROM = 0;
    private static final int TO = 1;
    private boolean isToEmpty;
    private boolean isFromEmpty;
    private Action onCancelClickAction;

    protected abstract void initUi(Context context, AttributeSet attrs);
    protected abstract TypedArray getAttrs();
    protected abstract int getAttrsTitleId();
//    protected abstract int getAttrsToId();
//    protected abstract int getAttrsFromId();
    protected abstract TextView getTitleView();
    protected abstract EditText getFromEditText();
    protected abstract EditText getToEditText();
//    protected abstract ImageButton getCancelBtn();


    public RangeViewAbs(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi(context, attrs);
        initAbsUi();
    }

    public RangeViewAbs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context, attrs);
        initAbsUi();
    }

    protected void initAbsUi(){
        if(getAttrs() != null){
            if(getTitleView() != null){
                String title = getAttrs().getString(getAttrsTitleId());
                if(title != null)
                    getTitleView().setText(title);
            }
        }

//        getCancelBtn().setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFromEditText().setText("");
//                getToEditText().setText("");
//                hideCancel();
//                if(onCancelClickAction != null)
//                    onCancelClickAction.execute();
//            }
//        });

        getFromEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() > 0){
//                    showCancel();
                    isFromEmpty = false;
                }else{
                    isFromEmpty = true;
//                    if(isToEmpty)
//                        hideCancel();
                }
            }
        });

        getToEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() > 0){
//                    showCancel();
                    isToEmpty = false;
                }else{
                    isToEmpty = true;
//                    if(isFromEmpty)
//                        hideCancel();
                }
            }
        });

    }

    protected String getNumberFromString(String str) {
        String val = str.replaceAll("[^\\d.]", "");
        if(str.contains("-"))
            val = "-" + val;
        return val;
    }

    public Double getFromValue(){
        String val = getNumberFromString(getFromEditText().getText().toString());
        if(!TextUtils.isEmpty(val))
            return Double.parseDouble(val);
        return null;
    }

    public Double getToValue(){
        String val = getNumberFromString(getToEditText().getText().toString());
        if(!TextUtils.isEmpty(val))
            return Double.parseDouble(val);
        return null;
    }


    protected String validateValue(int type, double from, double to, String insertedValue, String symbol){
        if(insertedValue.equals(""))
            return "";
        String correctValue = "";
        boolean isFromBigger = from > to;
        Double insertedValueD = Double.parseDouble(insertedValue.replaceAll("[^\\d.]", ""));
        if(isFromBigger){
            if(insertedValueD < from && insertedValueD > to ){
                correctValue = insertedValue;
            }else{
                if(type == FROM)
                    correctValue = String.valueOf(TextUtils.getShorterDouble(from, false));
                else
                    correctValue = String.valueOf(TextUtils.getShorterDouble(to, false));
            }
        }else{
            if(insertedValueD > from && insertedValueD < to){
                correctValue = insertedValue;
            }else{
                if(type == FROM)
                    correctValue = String.valueOf(from);
                else
                    correctValue = String.valueOf(to);
            }
        }
        return correctValue.contains(symbol) ? correctValue : (correctValue += symbol);
    }

//    protected void hideCancel(){
//        getTitleView().setTextColor(getResources().getColor(R.color.black));
//        getCancelBtn().setVisibility(GONE);
//    }
//
//    public void showCancel(){
//        getTitleView().setTextColor(getResources().getColor(R.color.black));
//        getCancelBtn().setVisibility(VISIBLE);
//    }


//    public void setOnCancelClickAction(Action action){
//        if(action != null)
//            this.onCancelClickAction = action;
//    }

    public void deselect() {
        getFromEditText().setText("");
        getToEditText().setText("");
//        hideCancel();
    }


}
