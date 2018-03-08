package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.monkeytechy.commonutils.DpUtils;
import com.monkeytechy.ui.activities.BaseActivity;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.ui.custom_views.listview_ext.OverScrollListView;

public class Test2Activity extends BaseActivity {

    private OverScrollListView listView;
    private View topHeaderView;

    private AbsListView.OnScrollListener onScrollListener;
    private boolean isScrollListenerDisabled = false;


    private static final int MOVING_AVERAGE_NUM_SAMPLES = 5;
    private float movingAverage = 0;
    private int[] osVal = new int[MOVING_AVERAGE_NUM_SAMPLES];
    private int osIndex = 0;
    private int lastOffset = 0;
    private static final int IMAGE_EXPAND_VELOCITY = 50;
    private static final int MAX_EXPAND_VAL = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);


        final LinearLayout headerTopLayout = (LinearLayout) findViewById(R.id.top_header_view);
        final RelativeLayout.LayoutParams headerTopLayoutParams = (RelativeLayout.LayoutParams) headerTopLayout.getLayoutParams();

        float scale = getResources().getDisplayMetrics().density;
        final int originalScale = (int) (252 * scale);

        listView = (OverScrollListView) findViewById(R.id.attraction_act_list_view);
        listView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        listView.setOverScrollOffsetY(200);

        final int[] counter = {0};
        final boolean[] isUp = {false};

        listView.setOverScrollListener(new OverScrollListView.OverScrolledListener() {
            @Override
            public void overScrolled(final int scrollY, int maxY, final boolean exceededOffset, boolean didFinishOverScroll) {
                osVal[(osIndex++) % MOVING_AVERAGE_NUM_SAMPLES] = scrollY;
                if (counter[0] == 4) {
                    counter[0] = 0;
                } else {
                    counter[0]++;
                }

                if (scrollY >= osVal[counter[0]])
                    isUp[0] = true;
                else
                    isUp[0] = false;

                for (int i = 0; i < 5; i++) {
                    Log.d("scroll", "scrollY " + i + " - " + osVal[i] + ", isUp - " + isUp[0]);
                }

                final int currHeight = headerTopLayoutParams.height;
                final int maxHeight = originalScale + scrollY;
                if (isUp[0]) {
                    if (currHeight < maxHeight && scrollY != 0) {
                        headerTopLayoutParams.height += 30;
                        headerTopLayout.setLayoutParams(headerTopLayoutParams);
                        headerTopLayout.requestLayout();
                    }
                }

                if (isUp[0] == false) {
                    if(currHeight > originalScale){
                        headerTopLayoutParams.height -= 30;
                        headerTopLayout.setLayoutParams(headerTopLayoutParams);
                        headerTopLayout.requestLayout();
                    }
                }

            }
        });

        topHeaderView = findViewById(R.id.top_header_view);
    }

    private void initTopHeaderChanger() {
//        if(true)return;
//        final ListView listView = (ListView) findViewById(R.id.attraction_act_list_view);
        if (onScrollListener == null) {
            onScrollListener = new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (isScrollListenerDisabled)
                        return;
                    int y = getScrollY();
                    Log.d("testh", y + "");
                    updateHeader(y);
//                if (DpUtils.getPixelsFromDP(getView().getContext(), 252) - y > DpUtils.getPixelsFromDP(getView().getContext(), 100)) {
//                    ViewGroup.LayoutParams lp = topHeaderView.getLayoutParams();
//                    lp.height = DpUtils.getPixelsFromDP(getView().getContext(), 252) - y;
//                    topHeaderView.setLayoutParams(lp);
//                }
                }
            };
        }
        listView.setOnScrollListener(onScrollListener);
    }

    private void updateHeader(int y) {
        int height = Math.max(-y, DpUtils.getPixelsFromDP(this, 100));
        topHeaderView.getLayoutParams().height = height;
        topHeaderView.requestLayout();
        findViewById(R.id.image_alpha_layout).setAlpha(1 - (((float) (height - DpUtils.getPixelsFromDP(this, 100))) / DpUtils.getPixelsFromDP(this, 152)));
    }

    public int getScrollY() {
        View c = listView.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int top = c.getTop();

        return -top;
    }

    private void setHeaderOriginalSize() {
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 252, r.getDisplayMetrics());
        final LinearLayout headerTopLayout = (LinearLayout) findViewById(R.id.top_header_view);
        final RelativeLayout.LayoutParams headerTopLayoutParams = (RelativeLayout.LayoutParams) headerTopLayout.getLayoutParams();
        headerTopLayoutParams.height = (int) px;
        headerTopLayout.setLayoutParams(headerTopLayoutParams);
        headerTopLayout.requestLayout();
    }
}
