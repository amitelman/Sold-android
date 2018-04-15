package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivitySavedSearchesBinding;
import sold.monkeytech.com.sold_android.databinding.MyServiceItemBinding;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.SavedSearch;
import sold.monkeytech.com.sold_android.framework.parsers.SavedSearchParser;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiDeleteSavedSearch;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiGetSavedSearch;
import sold.monkeytech.com.sold_android.ui.adapters.SavedSearchAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.ServiceItemFragment;

public class SavedSearchesActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySavedSearchesBinding mBinding;
    private SavedSearchAdapter adapter;
    private boolean isInEditMode = false;
    private boolean isAllSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_saved_searches);

        getSaved();
    }

    private void getSaved() {
        final Handler handler = new Handler();
        new ApiGetSavedSearch(this).request(new TAction<List<SavedSearch>>() {
            @Override
            public void execute(final List<SavedSearch> searches) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initUi(searches);
                    }
                });
            }
        }, new Action() {
            @Override
            public void execute() {

            }
        });
    }

    private void initUi(List<SavedSearch> searches) {
        adapter = new SavedSearchAdapter(this, searches, onSavedSelect());
        mBinding.savedSearchActListView.setAdapter(adapter);

        mBinding.savedSearchActBackBtn.setOnClickListener(this);
        mBinding.savedSearchActEditBtn.setOnClickListener(this);
        mBinding.savedSearchActCancelBtn.setOnClickListener(this);
        mBinding.savedSearchActDeleteBtn.setOnClickListener(this);
    }

    private TAction<SavedSearch> onSavedSelect() {
        return new TAction<SavedSearch>() {
            @Override
            public void execute(SavedSearch savedSearch) {
                SearchParamManager.getInstance().clearParams();
                if (savedSearch.getPropertyType().size() > 0)
                    SearchParamManager.getInstance().setTypesCsv(savedSearch.getPropertyTypeCsv());
                if (savedSearch.getFeatures().size() > 0)
                    SearchParamManager.getInstance().setFeaturesCsv(savedSearch.getFeaturesCsv());
                if (savedSearch.getMinFloorArea() != 0)
                    SearchParamManager.getInstance().setMinFloorArea(savedSearch.getMinFloorArea());
                if (savedSearch.getMaxFloorArea() != 0)
                    SearchParamManager.getInstance().setMaxFloorArea(savedSearch.getMaxFloorArea());
                if (savedSearch.getMinPlotArea() != 0)
                    SearchParamManager.getInstance().setMinPlotArea(savedSearch.getMinPlotArea());
                if (savedSearch.getMaxPlotArea() != 0)
                    SearchParamManager.getInstance().setMaxPlotArea(savedSearch.getMaxPlotArea());
                if (savedSearch.getMinFloors() != 0)
                    SearchParamManager.getInstance().setMinFloors(savedSearch.getMinFloors());
                if (savedSearch.getMaxFloors() != 0)
                    SearchParamManager.getInstance().setMaxFloors(savedSearch.getMaxFloors());
                if (savedSearch.getMinRooms() != 0)
                    SearchParamManager.getInstance().setMinRooms(savedSearch.getMinRooms());
                if (savedSearch.getMinBaths() != 0)
                    SearchParamManager.getInstance().setMinBaths(savedSearch.getMinBaths());
                if (savedSearch.getMinPrice() != null)
                    SearchParamManager.getInstance().setMinPrice(savedSearch.getMinPrice().getValue());
                if (savedSearch.getMaxPrice() != null)
                    SearchParamManager.getInstance().setMaxPrice(savedSearch.getMaxPrice().getValue());
                if (savedSearch.isHasOpenHouse())
                    SearchParamManager.getInstance().setHasOpenHouse(savedSearch.isHasOpenHouse());
                if (savedSearch.isHasParking())
                    SearchParamManager.getInstance().setHasParking(savedSearch.isHasParking());
                if (savedSearch.isHideForeclosures())
                    SearchParamManager.getInstance().setHideForeclosure(savedSearch.isHideForeclosures());
                if (savedSearch.isHideNewConstruction())
                    SearchParamManager.getInstance().setHideNewConstruction(savedSearch.isHideNewConstruction());
                finish();
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savedSearchActBackBtn:
                finish();
                break;
            case R.id.savedSearchActEditBtn:
                if (!isInEditMode) {
                    isInEditMode = true;
                    adapter.setAdapterType(SavedSearchAdapter.EDIT);
                    mBinding.savedSearchActEditBtn.setText("Select All");
                    mBinding.savedSearchActDeleteBtn.setVisibility(View.VISIBLE);
                    mBinding.savedSearchActCancelBtn.setVisibility(View.VISIBLE);
                    mBinding.savedSearchActBackBtn.setVisibility(View.GONE);
                } else {
                    if (!isAllSelected) {
                        adapter.selectAll();
                        isAllSelected = true;
                        mBinding.savedSearchActEditBtn.setText("Deselect All");
                    } else {
                        adapter.deselectAll();
                        isAllSelected = false;
                        mBinding.savedSearchActEditBtn.setText("Select All");
                    }
                }
                break;
            case R.id.savedSearchActCancelBtn:
                adapter.setAdapterType(SavedSearchAdapter.REGULAR);
                isInEditMode = false;
                adapter.deselectAll();
                mBinding.savedSearchActEditBtn.setText("Edit");
                mBinding.savedSearchActDeleteBtn.setVisibility(View.GONE);
                mBinding.savedSearchActCancelBtn.setVisibility(View.GONE);
                mBinding.savedSearchActBackBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.savedSearchActDeleteBtn:
                final List<SavedSearch> savedSearches = new ArrayList<>();
                savedSearches.addAll(adapter.getSelectedItems());
                if(savedSearches.size() > 0){
                    String csv = "";
                    for(SavedSearch ss : savedSearches){
                        csv += ss.getId() + ",";
                    }
                    adapter.deleteSelected(savedSearches);
                    final Handler handler = new   Handler();
                    new ApiDeleteSavedSearch(SavedSearchesActivity.this).request(csv, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SavedSearchesActivity.this, "Selected Searches deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.addToSelected(savedSearches);
                                    Toast.makeText(SavedSearchesActivity.this, "Delete failed, try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;
        }
    }
}
