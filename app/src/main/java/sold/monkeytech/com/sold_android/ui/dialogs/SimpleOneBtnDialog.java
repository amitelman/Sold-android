//package sold.monkeytech.com.sold_android.ui.dialogs;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;
//
//public class SimpleOneBtnDialog extends TransparentDialog implements View.OnClickListener {
//
//    private final Boolean isWithTopIcon;
//    private String title = "";
//    private String body = "";
//    private Action onDismiss;
//
//    public SimpleOneBtnDialog(Context context, String title, String body, Boolean isWithTopIcon, Action onDismiss) {
//        super(context);
//        this.title = title;
//        this.body = body;
//        this.isWithTopIcon = isWithTopIcon;
//        if(onDismiss != null)
//            this.onDismiss = onDismiss;
//    }
//
//
//    protected int getLayout(){
//        return R.layout.dialog_simple_one_btn;
//    }
//
//    protected void loadUI(){
//        ImageView closeBtn = (ImageView) findViewById(R.id.simpleDialogClose);
//        TextView title = (TextView) findViewById(R.id.simpleDialogTitle);
//        TextView body = (TextView) findViewById(R.id.simpleDialogBody);
//        TextView okBtn = (TextView) findViewById(R.id.simpleDialogOkBtn);
//
//        title.setText(this.title);
//        body.setText(this.body);
//
//        okBtn.setOnClickListener(this);
//        closeBtn.setOnClickListener(this);
//
//        if(isWithTopIcon != null && isWithTopIcon){
//            findViewById(R.id.simpleDialogClose).setVisibility(View.VISIBLE);
//        }else{
//            findViewById(R.id.simpleDialogClose).setVisibility(View.GONE);
//        }
//
//
//    }
//
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
//
//    @Override
//    public void show(){
//        super.show();
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.simpleDialogBody:
//            case R.id.simpleDialogOkBtn:
//                if(onDismiss != null)
//                    onDismiss.execute();
//                dismiss();
//                break;
//        }
//    }
//
//}
