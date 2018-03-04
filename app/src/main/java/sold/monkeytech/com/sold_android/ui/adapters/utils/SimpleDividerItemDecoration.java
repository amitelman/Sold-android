//package sold.monkeytech.com.sold_android.ui.adapters.utils;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import sold.monkeytech.com.sold_android.R;
//import sold.monkeytech.com.sold_android.framework.Utils.DpUtils;
//
//
///**
// * Created by MonkeyFather on 29/08/2017.
// */
//
//public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
//
//    public static final int RECYCLERvIEW_SPACING = 0;
//    public static final int LISTVIEW_SAPCING = 1;
//
//
//    private final Context context;
//    private Drawable mDivider;
//
//    public SimpleDividerItemDecoration(Context context){//}), int type) {
//        this.context = context;
////        if(type == RECYCLERvIEW_SPACING)
//            mDivider = ContextCompat.getDrawable(context, R.drawable.recyclerview_spacing);
////        else
////            mDivider = ContextCompat.getDrawable(context, R.drawable.listview_spacing);
//    }
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//
//        if (parent.getChildAdapterPosition(view) == 0) {
//            return;
//        }
//
//        outRect.left = (int) DpUtils.toPx(context, 11.3f);
//    }
//}