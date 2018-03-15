package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityTalkToAgentBinding;

public class TalkToAgentActivity extends Activity {

    private ActivityTalkToAgentBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_talk_to_agent);
    }
}
