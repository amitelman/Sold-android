package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivitySold3DtourBinding;
import sold.monkeytech.com.sold_android.framework.models.Property;

public class Sold3DTourActivity extends Activity {

    private ActivitySold3DtourBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sold3_dtour);

        initUi();
    }

    private void initUi() {
        final String link = getIntent().getStringExtra("link");

        mBinding.sold3DTourActWebView.getSettings().setJavaScriptEnabled(true);
        mBinding.sold3DTourActWebView.loadUrl(link);

        mBinding.sold3DTourActClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.sold3DTourActShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "check this 3d tour: " + link);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    public static void startWithLink(Context context, String tourLink) {
        Intent intent = new Intent(context, Sold3DTourActivity.class);
        intent.putExtra("link", tourLink);
        context.startActivity(intent);

    }
}
