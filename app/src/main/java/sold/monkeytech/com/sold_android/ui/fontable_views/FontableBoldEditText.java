package sold.monkeytech.com.sold_android.ui.fontable_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import sold.monkeytech.com.sold_android.ui.fontable_views.abs.CustomFontGenerator;


public class FontableBoldEditText extends android.support.v7.widget.AppCompatEditText {
    public FontableBoldEditText(Context context) {
        super(context);
        CustomFontGenerator.setDefaultFont(this);
    }

    public FontableBoldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontGenerator.setCustomFont(context, attrs, this);
    }

    public FontableBoldEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontGenerator.setCustomFont(context, attrs, this);
    }
}
