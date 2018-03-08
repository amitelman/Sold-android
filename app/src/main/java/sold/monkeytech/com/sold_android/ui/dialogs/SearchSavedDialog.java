package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class SearchSavedDialog extends TransparentDialog implements View.OnClickListener {

    private Action onDismiss;

    public SearchSavedDialog(Context context, Action onDismiss) {
        super(context);
        if(onDismiss != null)
            this.onDismiss = onDismiss;
    }


    protected int getLayout(){
        return R.layout.dialog_simple_one_btn;
    }

    protected void loadUI(){
        ImageButton closeBtn = findViewById(R.id.simpleDialogClose);
        TextView continueBtn = findViewById(R.id.searchSavedDialogContinue);

        closeBtn.setOnClickListener(this);
        continueBtn.setOnClickListener(this);

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
