package sold.monkeytech.com.sold_android.framework.Utils;

/**
 * Created by MonkeyFather on 29/08/2017.
 */

import android.content.Context;

/**
 * Created by elitu on 04/04/2017.
 */

public class DpUtils {

    public static float toPx(Context context,float dp){
        float density = context.getResources().getDisplayMetrics().density;
        float px = dp * density;
//        float dp = somePxValue / density;
        return px;
    }
}