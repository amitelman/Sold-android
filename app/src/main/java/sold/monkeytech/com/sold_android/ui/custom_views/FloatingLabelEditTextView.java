//package sold.monkeytech.com.sold_android.ui.custom_views;
//
//import android.content.Context;
//import android.content.res.ColorStateList;
//import android.content.res.TypedArray;
//import android.databinding.DataBindingUtil;
//import android.graphics.Typeface;
//import android.support.annotation.ColorInt;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TextInputLayout;
//import android.text.Editable;
//import android.text.InputType;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//
//import com.mid.mid.R;
//import com.mid.mid.databinding.FloatingLabelEditTextViewBinding;
//
//import java.lang.reflect.Field;
//
///**
// * Created by MonkeyFather on 20/07/2017.
// */
//
//public class FloatingLabelEditTextView extends LinearLayout {
//
//    private static final int TEXT = 0;
//    private static final int NUMBERS = 1;
//
//    private FloatingLabelEditTextViewBinding mBinding;
//
//    public FloatingLabelEditTextView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initUi(context, attrs);
//    }
//
//    public FloatingLabelEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initUi(context, attrs);
//    }
//
//    public void initUi(Context context, AttributeSet attrs) {
//        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.floating_label_edit_text_view, this, true);
//
//        final Typeface bold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
//        final Typeface reg = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
//        mBinding.floatingLabelEditTextLayout.getEditText().setTypeface(reg);
//        mBinding.floatingLabelEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if(hasFocus){
//                    mBinding.floatingLabelEditTextLayout.setTypeface(bold);
//                }else{
//                    String str = mBinding.floatingLabelEditTextLayout.getEditText().getText().toString();
//                    if(str.length() > 0){
//                        mBinding.floatingLabelEditTextLayout.setTypeface(bold);
//                    }else{
//                        mBinding.floatingLabelEditTextLayout.setTypeface(reg);
//                    }
//                }
//
//                boolean isOrange = !((EditText)view).getText().toString().isEmpty() || view.isFocused();
//                setInputTextLayoutColor(mBinding.floatingLabelEditTextLayout,getContext().getResources().getColor(isOrange?R.color.orange:R.color.warm_grey));
//            }
//        });
//        mBinding.floatingLabelEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                boolean isOrange = !editable.toString().isEmpty() || mBinding.floatingLabelEditText.isFocused();
//                setInputTextLayoutColor(mBinding.floatingLabelEditTextLayout,getContext().getResources().getColor(isOrange?R.color.orange:R.color.warm_grey));
//            }
//        });
//
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FloatingLabelValues);
//        //set hint
//        String hint = a.getString(R.styleable.FloatingLabelValues_hint);
//        mBinding.floatingLabelEditTextLayout.setHint(hint);
//
//        int inputType = a.getInt(R.styleable.FloatingLabelValues_inputType, 0);
//        setInputType(inputType);
//
//        boolean isEditable = a.getBoolean(R.styleable.FloatingLabelValues_isEditable, true);
//        if(!isEditable){
//            mBinding.floatingLabelEditText.setFocusable(false);
//            mBinding.floatingLabelEditText.setEnabled(false);
//            mBinding.floatingLabelEditTextLayout.setFocusable(false);
//            mBinding.floatingLabelEditTextLayout.setEnabled(false);
//        }
//
//    }
//
//    public static void setInputTextLayoutColor(TextInputLayout til, @ColorInt int color) {
//        try {
//            Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
//            fDefaultTextColor.setAccessible(true);
//            fDefaultTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{ color }));
////            Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
////            fFocusedTextColor.setAccessible(true);
////            fFocusedTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{ color }));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setText(String str){
//        mBinding.floatingLabelEditText.setText(str);
//    }
//
//    public String getText(){
//        return String.valueOf(mBinding.floatingLabelEditText.getText());
//    }
//
//    public void setInputType(int type){
//        switch (type){
//            case TEXT:
//                mBinding.floatingLabelEditText.setInputType(InputType.TYPE_CLASS_TEXT);
//                break;
//            case NUMBERS:
//                mBinding.floatingLabelEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                break;
//        }
//    }
//}