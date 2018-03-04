//package sold.monkeytech.com.sold_android.ui.dialogs;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Rect;
//import android.view.MotionEvent;
//
//import com.mid.mid.ui.dialogs.abs.TransparentDialog;
//import com.monkeytechy.framework.interfaces.Action;
//
//public class ErrorDialog extends TransparentDialog {
//
//
//    private final Context context;
//    private Action onDismissAction = null;
//
//    public ErrorDialog(Context context, Action onDismiss) {
//        super(context);
//        this.context = context;
//        this.onDismissAction = onDismiss;
//    }
//
//    public ErrorDialog(Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    protected int getLayout(){
//        return 0;
//    }
//
//    protected void loadUI(){
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
//        SimpleOneBtnDialog dialog = new SimpleOneBtnDialog(context, "Error!","An error has occured, please try again after making sure data is valid", false, null);
//        dialog.show();
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                if(onDismissAction != null)
//                    onDismissAction.execute();
//            }
//        });
////        super.show();
//    }
//
//}
