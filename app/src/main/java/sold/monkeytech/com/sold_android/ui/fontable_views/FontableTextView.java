package sold.monkeytech.com.sold_android.ui.fontable_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.ui.fontable_views.abs.CustomFontGenerator;


public class FontableTextView extends android.support.v7.widget.AppCompatTextView {

    public FontableTextView(Context context) {
        super(context);
        CustomFontGenerator.setDefaultFont(this);
    }

    public FontableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontGenerator.setCustomFont(context, attrs, this);
    }

    public FontableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontGenerator.setCustomFont(context, attrs, this);
    }

    public void setFont(Context context, Integer font){
        CustomFontGenerator.setCustomFont(context, font, this);
    }


}
