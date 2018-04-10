package sold.monkeytech.com.sold_android.ui.dialogs.abs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import sold.monkeytech.com.sold_android.R;


public  class TransparentDialog extends Dialog {
    public TransparentDialog(Context context) {
        this(context,android.R.style.Theme_Black_NoTitleBar);
    }

    public TransparentDialog(Context context, int theme) {
        super(context, theme);
    }

    protected TransparentDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(R.color.black_70)));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);
        setContentView(getLayout());
        if (findViewById(R.id.outside_layout)!=null)
            findViewById(R.id.outside_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
//        if (findViewById(R.id.x_btn)!=null)
//            findViewById(R.id.x_btn).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dismiss();
//                }
//            });
        loadUI();
    }

    protected int getLayout(){return 0;}

    protected void loadUI(){}

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Rect dialogBounds = new Rect();
//        getWindow().getDecorView().getHitRect(dialogBounds);
//
//        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
//            // Tapped outside so we finish the activity
//            this.dismiss();
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public void show(){
        getWindow().getAttributes().windowAnimations = R.style.MyAnimation;
        super.show();
    }
}
