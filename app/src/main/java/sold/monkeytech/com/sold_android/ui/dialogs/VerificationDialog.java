package sold.monkeytech.com.sold_android.ui.dialogs;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.KeyboardAndInputUtils;
import sold.monkeytech.com.sold_android.framework.serverapi.auth.ApiValidateCode;
import sold.monkeytech.com.sold_android.ui.custom_views.NumPadView;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class VerificationDialog extends TransparentDialog implements View.OnClickListener {

    private final String phoneNumber;
    private Action onValidateApproved;
    private List<Character> codeBuilder;
    private String codeString = "";

    public VerificationDialog(Context context, String phoneNumber, Action onValidateApproved) {
        super(context);
        this.phoneNumber = phoneNumber;
        if(onValidateApproved != null)
            this.onValidateApproved = onValidateApproved;
    }


    protected int getLayout(){
        return R.layout.dialog_verification;
    }

    protected void loadUI(){
        codeBuilder = new ArrayList<>();

        final NumPadView numPadView = findViewById(R.id.verificationDialogNumPadView);
        numPadView.getInputEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                codeString = numPadView.getInputEditText().getText().toString();
                if (codeString.length() > 4) {
                    numPadView.getInputEditText().setText(codeString.substring(0, 3) + codeString.charAt(codeString.length() - 1));
                    return;
                }
                if (codeString.length() >= 1) {
                    ((TextView) findViewById(R.id.signupDialogInput1)).setText(codeString);
                } else {
                    ((TextView) findViewById(R.id.signupDialogInput1)).setText("");
                }
                if (codeString.length() >= 2) {
                    ((TextView) findViewById(R.id.signupDialogInput2)).setText(codeString.substring(1));
                } else {
                    ((TextView) findViewById(R.id.signupDialogInput2)).setText("");
                }
                if (codeString.length() >= 3) {
                    ((TextView) findViewById(R.id.signupDialogInput3)).setText(codeString.substring(2));
                } else {
                    ((TextView) findViewById(R.id.signupDialogInput3)).setText("");
                }
                if (codeString.length() >= 4) {
                    ((TextView) findViewById(R.id.signupDialogInput4)).setText(codeString.substring(3));
                    sendCode();

                } else {
                    ((TextView) findViewById(R.id.signupDialogInput4)).setText("");
                }
            }
        });
    }

    private void sendCode() {
        new ApiValidateCode(getContext()).request(phoneNumber, codeString, new Action() {
            @Override
            public void execute() {
                dismiss();
                if(onValidateApproved != null)
                    onValidateApproved.execute();
            }
        }, null); //todo: add on failed action
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
                sendCode();
                break;
        }
    }

}
