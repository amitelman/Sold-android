package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.monkeytechy.ui.activities.BaseActivity;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivitySortFiltersBinding;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.ui.adapters.SortFilterAdapter;

public class SortFiltersActivity extends BaseActivity {

    private ActivitySortFiltersBinding mBinding;
    private SortFilterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sort_filters);
        initUi();
    }

    private void initUi() {

        List<IdLabel> sortables = MetaDataManager.getInstance().getSortables();
        adapter = new SortFilterAdapter(this, sortables);
        mBinding.sortActListView.setAdapter(adapter);

        mBinding.sortActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndFinish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        saveAndFinish();
//        super.onBackPressed();
    }

    private void saveAndFinish() {
        if(adapter != null){
            long sortId = adapter.getSotrableId();
            String direction = adapter.getSortDirection();
            SearchParamManager.getInstance().updateParams("sortable_id", (int) sortId);
            SearchParamManager.getInstance().updateParams("sort_direction", direction);
//            SearchParamManager.getInstance().setSortId(sortId);
//            SearchParamManager.getInstance().setSortDirection(direction);
            finish();
        }
    }
}

