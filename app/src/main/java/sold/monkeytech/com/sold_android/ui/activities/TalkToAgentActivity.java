package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityTalkToAgentBinding;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.serverapi.auth.ApiGetCode;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiPostLead;
import sold.monkeytech.com.sold_android.ui.custom_views.SoldProgressBar;

public class TalkToAgentActivity extends Activity {

    private ActivityTalkToAgentBinding mBinding;
    private long propertyId = 0;
    private long slotId = 0;
    private String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_talk_to_agent);

        if (getIntent() != null) {
            propertyId = getIntent().getLongExtra("propertyId", 0);
            slotId = getIntent().getLongExtra("slotId", 0);
            description = getIntent().getStringExtra("description");
        }

        initUi();
    }

    private void initUi() {
        mBinding.talkToAgentActContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateFields()) {
                    ((SoldProgressBar) findViewById(R.id.talkToAgentActPb)).show();
                    String firstName = ((TextView) findViewById(R.id.talkToAgentActFirstName)).getText().toString();
                    String lastName = ((TextView) findViewById(R.id.talkToAgentActLastName)).getText().toString();
                    final String description = mBinding.talkToAgentActDescription.getText().toString();
                    String email = mBinding.talkToAgentActEmail.getText().toString();
                    final String phone = mBinding.talkToAgentActPhone.getText().toString();
                    final Handler handler = new Handler();

                    new ApiGetCode(TalkToAgentActivity.this).request(phone, firstName, lastName, email,
                            UserManager.getInstance().getValidationDialog(TalkToAgentActivity.this, phone, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    new ApiPostLead(TalkToAgentActivity.this).request((int) propertyId, (int) slotId, description, new Action() {
                                        @Override
                                        public void execute() {
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(TalkToAgentActivity.this, "Your request has been sent to an agent", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            });
                                        }
                                    }, new Action() {
                                        @Override
                                        public void execute() {
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(TalkToAgentActivity.this, "An error accord, Please try again later", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    }), new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SoldProgressBar) findViewById(R.id.talkToAgentActPb)).hide();
                                    Toast.makeText(TalkToAgentActivity.this, "Server Error, Please try again later...", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });



                }
            }
        });
    }

    private boolean ValidateFields() {
        String firstName = ((TextView) findViewById(R.id.talkToAgentActFirstName)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.talkToAgentActLastName)).getText().toString();
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please Fill Your Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        String email = ((TextView) findViewById(R.id.talkToAgentActEmail)).getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Fill Your Email", Toast.LENGTH_SHORT).show();
            return false;
        }
        String phone = ((TextView) findViewById(R.id.talkToAgentActPhone)).getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please Fill Your Phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!((CheckBox) findViewById(R.id.talkToAgentActCb)).isChecked()) {
            Toast.makeText(this, "Please Accept Privacy Policy", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

//    new ApiPostLead(getContext()).request(propertyId, slotId, description, new Action() {
//        @Override
//        public void execute() {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getContext(), "Your request has been sent to an agent", Toast.LENGTH_SHORT).show();
//                }
//            });
//            dismiss();
//        }
//    }, new Action() {
//        @Override
//        public void execute() {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getContext(), "An error accord, Please try again later", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    });
}
