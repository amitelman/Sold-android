package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityPrivateShowingBinding;

public class PrivateShowingActivity extends BaseActivity {

    private ActivityPrivateShowingBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_private_showing);
    }
}
