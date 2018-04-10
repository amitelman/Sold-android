package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.serverapi.auth.ApiGetCode;
import sold.monkeytech.com.sold_android.ui.custom_views.SoldProgressBar;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class LoginDialog extends TransparentDialog implements View.OnClickListener {

    private TAction<String> onDismiss;

    public LoginDialog(Context context, TAction<String> onDismiss) {
        super(context);
        if(onDismiss != null)
            this.onDismiss = onDismiss;
    }


    protected int getLayout(){
        return R.layout.dialog_login;
    }

    protected void loadUI(){

        ImageButton closeBtn = findViewById(R.id.loginDialogClose);
        TextView continueBtn = findViewById(R.id.loginDialogDoneBtn);

        closeBtn.setOnClickListener(this);
        continueBtn.setOnClickListener(this);

    }


    @Override
    public void show(){
        super.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginDialogClose:
                dismiss();
                break;
            case R.id.loginDialogDoneBtn:
//                if (onDismiss != null)
//                    onDismiss.execute();
//                break;
                if(ValidateFields()){
                    ((SoldProgressBar)findViewById(R.id.loginDialogPb)).show();
                    final String phone = ((TextView) findViewById(R.id.loginDialogPhone)).getText().toString();
                    final Handler handler = new Handler();
                    new ApiGetCode(getContext()).request(phone, "", "", "", new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SoldProgressBar)findViewById(R.id.loginDialogPb)).hide();
                                    if (onDismiss != null)
                                        onDismiss.execute(phone);
                                    dismiss();
                                }
                            });
                        }
                    }, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SoldProgressBar)findViewById(R.id.loginDialogPb)).hide();
                                    Toast.makeText(getContext(), "Server Error, Please try again later...", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;
        }
    }

    private boolean ValidateFields() {
        String phone = ((TextView) findViewById(R.id.loginDialogPhone)).getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(getContext(), "Please Fill Your Phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

}
