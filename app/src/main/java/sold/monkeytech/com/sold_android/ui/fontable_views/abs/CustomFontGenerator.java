package sold.monkeytech.com.sold_android.ui.fontable_views.abs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


import java.util.Hashtable;

import sold.monkeytech.com.sold_android.R;

/**
 * Created by elitu on 9/3/14.
 */
public class CustomFontGenerator {
    private static Hashtable<Integer, Typeface> fontCache = new Hashtable<Integer, Typeface>();

    public static void setCustomFont(Context ctx,  AttributeSet attrs, TextView view ) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontableView);
        Integer customFont = a.getInteger(R.styleable.FontableView_fontType,0);
        setCustomFont(ctx, customFont,view);
        a.recycle();
    }

    public static boolean setCustomFont(Context ctx, Integer asset, TextView view) {
        Typeface tf = null;
        try {
            switch (asset) {
                case 0:
                    if (fontCache.contains(asset))
                        tf = fontCache.get(asset);
                    else {
                        tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/Muli-Regular.ttf");
                        fontCache.put(asset, tf);
                    }
                    break;
                case 1:
                    if (fontCache.contains(asset))
                        tf = fontCache.get(asset);
                    else {
                        tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/Mult-Light.ttf");
                        fontCache.put(asset, tf);
                    }
                    break;
                case 2:
                    if (fontCache.contains(asset))
                        tf = fontCache.get(asset);
                    else {
                        tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/Muli-Bold.ttf");
                        fontCache.put(asset, tf);
                    }
                    break;
                case 3:
                    if (fontCache.contains(asset))
                        tf = fontCache.get(asset);
                    else {
                        tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/Muli-ExtraBold.ttf");
                        fontCache.put(asset, tf);
                    }
                    break;


            }
        } catch (Exception e) {
            Log.e("fontChange", "Could not get typeface: " + e.getMessage());
            return false;
        }
        view.setTypeface(tf);
        return true;
    }

    public static void setDefaultFont(TextView view){
        Typeface font = Typeface.createFromAsset(view.getContext().getAssets(),"fonts/Muli-Regular.ttf");
        view.setTypeface(font);
    }
}
