//package sold.monkeytech.com.sold_android.ui.dialogs;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.mid.mid.R;
//import com.mid.mid.ui.dialogs.abs.TransparentDialog;
//import com.monkeytechy.framework.interfaces.TAction;
//
//public class EditTextDialog extends TransparentDialog implements View.OnClickListener {
//
//    private final TAction<Double> onOkPressed;
//    private String curValue = "";
//    private String title = "";
//
//    public EditTextDialog(Context context, String title, String curValue, TAction<Double> onOkPressed) {
//        super(context);
//        this.title = title;
//        this.onOkPressed = onOkPressed;
//        this.curValue = curValue;
//    }
//
//    protected int getLayout(){
//        return R.layout.dialog_edit_text;
//    }
//
//    protected void loadUI(){
//        TextView title = (TextView) findViewById(R.id.editDialogTitle);
//        TextView okBtn = (TextView) findViewById(R.id.editDialogOkBtn);
//        TextView cancelBtn = (TextView) findViewById(R.id.editDialogCancelBtn);
//        EditText input = (EditText) findViewById(R.id.editDialogInput);
//        input.setHint(curValue);
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//
//        title.setText("Enter new value for " + this.title);
//
//        okBtn.setOnClickListener(this);
//        cancelBtn.setOnClickListener(this);
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
//            case R.id.editDialogOkBtn:
//                if(onOkPressed != null){
//                    double newVal = Double.parseDouble(((EditText)findViewById(R.id.editDialogInput)).getText().toString());
//                    onOkPressed.execute(newVal);
//                }
//                dismiss();
//                break;
//            case R.id.editDialogCancelBtn:
//                dismiss();
//                break;
//        }
//    }
//}
