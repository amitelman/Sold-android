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
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiPostLead;
import sold.monkeytech.com.sold_android.ui.custom_views.SoldProgressBar;
import sold.monkeytech.com.sold_android.ui.dialogs.abs.TransparentDialog;

public class TalkToAgentDialog extends TransparentDialog implements View.OnClickListener {

    private int slotId;
    private int propertyId;
    private TAction<String> onDismiss;

    public TalkToAgentDialog(Context context, int propertyId, int slotId, TAction<String> onDismiss) {
        super(context);
        if(onDismiss != null)
            this.onDismiss = onDismiss;
        if(propertyId != 0)
            this.propertyId = propertyId;
        if(slotId != 0)
            this.slotId = slotId;
    }


    protected int getLayout(){
        return R.layout.dialog_talk_to_agent;
    }

    protected void loadUI(){

        ImageButton closeBtn = findViewById(R.id.agentDialogClose);
        TextView continueBtn = findViewById(R.id.agentDialogDoneBtn);

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
            case R.id.agentDialogClose:
                dismiss();
                break;
            case R.id.agentDialogDoneBtn:
                if(ValidateFields()){
                    final Handler handler = new Handler();
                    String description = ((TextView) findViewById(R.id.agentDialogDescription)).getText().toString();
                    new ApiPostLead(getContext()).request(propertyId, slotId, description, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Your request has been sent to an agent", Toast.LENGTH_SHORT).show();
                                }
                            });
                            dismiss();
                        }
                    }, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "An error accord, Please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;
        }
    }

    private boolean ValidateFields() {
        String description = ((TextView) findViewById(R.id.agentDialogDescription)).getText().toString();
        if(TextUtils.isEmpty(description)){
            Toast.makeText(getContext(), "Please Add Your Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!((CheckBox)findViewById(R.id.agentDialogCb)).isChecked()){
            Toast.makeText(getContext(), "Please Agree to Privacy Policy", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

}
