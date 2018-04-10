package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityFavoriteBinding;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiGetFavorites;
import sold.monkeytech.com.sold_android.ui.adapters.SearchInListAdapter;

public class FavoriteActivity extends BaseActivity {

    private ActivityFavoriteBinding mBinding;
    private SearchInListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite);
        adapter = new SearchInListAdapter(FavoriteActivity.this, null);
        mBinding.favoriteActListView.setAdapter(adapter);
        initUi();

    }

    private void initUi() {
        mBinding.favoriteActPb.show();
        final Handler handler = new Handler();
        new ApiGetFavorites(this).request(new TAction<List<Property>>() {
            @Override
            public void execute(final List<Property> properties) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateList(properties);
                        mBinding.favoriteActPb.hide();
                    }
                });
            }
        }, new TAction<String>() {
            @Override
            public void execute(String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.favoriteActPb.hide();
                    }
                });
            }
        });

        mBinding.favoriteActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
