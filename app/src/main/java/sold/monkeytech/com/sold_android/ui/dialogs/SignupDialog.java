package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
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

public class SignupDialog extends TransparentDialog implements View.OnClickListener {

    private TAction<String> onDismiss;
    private Action onLoginPress;

    public SignupDialog(Context context, TAction<String> onDismiss, Action onLoginPress) {
        super(context);
        if(onDismiss != null)
            this.onDismiss = onDismiss;
        if(onLoginPress != null)
            this.onLoginPress = onLoginPress;
    }


    protected int getLayout(){
        return R.layout.dialog_signup;
    }

    protected void loadUI(){
        Log.d("wowSignup","signup dialog start");
        ImageButton closeBtn = findViewById(R.id.signupDialogClose);
        TextView continueBtn = findViewById(R.id.signupDialogDoneBtn);
        TextView loginBtn = findViewById(R.id.signupDialogLogin);

        closeBtn.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }


    @Override
    public void show(){
        super.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupDialogClose:
                dismiss();
                break;
            case R.id.signupDialogDoneBtn:
//                if (onDismiss != null)
//                    onDismiss.execute();
//                break;
                if(ValidateFields()){
                    ((SoldProgressBar)findViewById(R.id.signupDialogPb)).show();
                    String firstName = ((TextView) findViewById(R.id.signupDialogFirstName)).getText().toString();
                    String lastName = ((TextView) findViewById(R.id.signupDialogLastName)).getText().toString();
                    String email = ((TextView) findViewById(R.id.signupDialogEmail)).getText().toString();
                    final String phone = ((TextView) findViewById(R.id.signupDialogPhone)).getText().toString();
                    final Handler handler = new Handler();
                    new ApiGetCode(getContext()).request(phone, firstName, lastName, email, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SoldProgressBar)findViewById(R.id.signupDialogPb)).hide();
                                    dismiss();
                                    if (onDismiss != null)
                                        onDismiss.execute(phone);
                                }
                            });
                        }
                    }, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SoldProgressBar)findViewById(R.id.signupDialogPb)).hide();
                                    Toast.makeText(getContext(), "Server Error, Please try again later...", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;
            case R.id.signupDialogLogin:
                dismiss();
                if(onLoginPress != null)
                    onLoginPress.execute();
                break;
        }
    }

    private boolean ValidateFields() {
        String firstName = ((TextView) findViewById(R.id.signupDialogFirstName)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.signupDialogLastName)).getText().toString();
        if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)){
            Toast.makeText(getContext(), "Please Fill Your Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        String email = ((TextView) findViewById(R.id.signupDialogEmail)).getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getContext(), "Please Fill Your Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        String phone = ((TextView) findViewById(R.id.signupDialogPhone)).getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(getContext(), "Please Fill Your Phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!((CheckBox) findViewById(R.id.signupDialogCb)).isChecked()){
            Toast.makeText(getContext(), "Please Accept Privacy Policy", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

}
