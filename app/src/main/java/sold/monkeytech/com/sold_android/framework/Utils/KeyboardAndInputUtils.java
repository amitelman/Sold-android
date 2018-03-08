package sold.monkeytech.com.sold_android.framework.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sold.monkeytech.com.sold_android.R;


/**
 * Created by MonkeyFather on 12/07/2017.
 */

public class KeyboardAndInputUtils {

    public static final int GREY = 0;
    public static final int WHITE = 1;
    public static final int TURQUISE = 2;
    public static final int WARM_GREY = 3;
    public static final int BLACK = 4;

    public static final int REG = 100;
    public static final int BOLD = 101;
    public static final int FONT_BLACK = 102;


    public static void closeKeyboard(Activity act){
        act.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void openKeyboard(Context context, EditText editText){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(editText.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }





}
