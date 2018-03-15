package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class VerificationDialog extends TransparentDialog implements View.OnClickListener {

    private Action onDismiss;

    public VerificationDialog(Context context, Action onDismiss) {
        super(context);
        if(onDismiss != null)
            this.onDismiss = onDismiss;
    }


    protected int getLayout(){
        return R.layout.dialog_signup;
    }

    protected void loadUI(){
//        ImageButton closeBtn = findViewById(R.id.simpleDialogClose);
//        TextView continueBtn = findViewById(R.id.searchSavedDialogContinue);

//        closeBtn.setOnClickListener(this);
//        continueBtn.setOnClickListener(this);



    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        Log.i("wowSignup", String.valueOf(event.getKeyCode()));
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);

        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            this.dismiss();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void show(){
        super.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.simpleDialogClose:
                dismiss();
                break;
            case R.id.searchSavedDialogContinue:
                if(onDismiss != null)
                    onDismiss.execute();
                dismiss();
                break;
        }
    }

}
