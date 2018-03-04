package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class SimpleTwoBtnDialog extends TransparentDialog implements View.OnClickListener {

    private final Action onOkPressed;
    private final Boolean isWithTopIcon;
    private String title = "";
    private String body = "";

    public SimpleTwoBtnDialog(Context context, String title, String body, Action onOkPressed, Boolean isWithTopIcon) {
        super(context);
        this.title = title;
        if(body != null)
            this.body = body;
        this.onOkPressed = onOkPressed;
        this.isWithTopIcon = isWithTopIcon;

    }

    protected int getLayout(){
        return  R.layout.dialog_simple_two_btn;
    }

    protected void loadUI(){
//        ImageButton closeBtn = (ImageButton) findViewById(R.id.simpleDialogClose);
        TextView title = (TextView) findViewById(R.id.simpleDialogTitle);
        TextView body = (TextView) findViewById(R.id.simpleDialogBody);
        TextView okBtn = (TextView) findViewById(R.id.simpleDialogOkBtn);
        TextView cancelBtn = (TextView) findViewById(R.id.simpleDialogCancelBtn);

        if(isWithTopIcon != null && isWithTopIcon){
            findViewById(R.id.simpleDialogTopIcon).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.simpleDialogTopIcon).setVisibility(View.GONE);
        }

        title.setText(this.title);
        if(body != null)
            body.setText(this.body);
        else{
            body.setVisibility(View.GONE);
        }

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
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
            case R.id.simpleDialogBody:
                dismiss();
                break;
            case R.id.simpleDialogOkBtn:
                if(onOkPressed != null)
                    onOkPressed.execute();
                dismiss();
                break;
            case R.id.simpleDialogCancelBtn:
                dismiss();
                break;
        }
    }
}
