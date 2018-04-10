package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityFilterSearchBinding;
import sold.monkeytech.com.sold_android.framework.Utils.KeyboardAndInputUtils;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiSaveSearch;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyAdditionalFeaturesAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyTypeAdapter;
import sold.monkeytech.com.sold_android.ui.fontable_views.FontableTextView;

public class FilterSearchActivity extends Activity implements View.OnClickListener {

    private ActivityFilterSearchBinding mBinding;
    private PropertyTypeAdapter typesAdapter;
    private PropertyAdditionalFeaturesAdapter featuresAdapter;


    //VALUES
    String typesCsv = "";
    String featuresCsv = "";
    int minFloorArea;
    int maxFloorArea;
    int minPlotArea;
    int maxPlotArea;
    int minPrice;
    int maxPrice;
    int minFloors;
    int maxFloors;
    int minRooms;
    int minBaths;
    boolean hasOpenHouse;
    boolean hideForeclosure;
    boolean hasContinent;
    boolean hideNewConstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter_search);

        initUi();
    }

    private void initUi() {
        KeyboardAndInputUtils.closeKeyboard(this);
        initPropertyBtns();
        initExpandableLayout();
        initAddFeaturesBtns();
        initBtns();
    }

    private void initBtns() {
        mBinding.filterActResetBtn.setOnClickListener(this);
        mBinding.filterActSaveSearchBtn.setOnClickListener(this);
        mBinding.filterActNameDeleteBtn.setOnClickListener(this);
        mBinding.filterActSaveBtn.setOnClickListener(this);

        mBinding.filterActNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    mBinding.filterActNameDeleteBtn.setVisibility(View.VISIBLE);
                }else{
                    mBinding.filterActNameDeleteBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filterActResetBtn:
                resetAllFields();
                break;
            case R.id.filterActSaveSearchBtn:
                if(mBinding.filterActSaveLayout.getVisibility() == View.GONE){
                    MyAnimationUtils.expand(mBinding.filterActSaveLayout);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.filterActScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 500);
                }
                break;
            case R.id.filterActNameDeleteBtn:
                mBinding.filterActNameInput.setText("");
                break;
            case R.id.filterActSaveBtn:
                if(!TextUtils.isEmpty(mBinding.filterActNameInput.getText().toString())){
                    saveSearch();
                }else{
                    Toast.makeText(FilterSearchActivity.this, "Please set a title to search", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.filterActApplyBtn:
                applyFilters();
                break;
        }
    }

    private void saveSearch() {
        double lat;
        double lng;
        String name = mBinding.filterActNameInput.getText().toString();
        if(SearchParamManager.getInstance().getLastSearchLatLng() != null){
            lat = SearchParamManager.getInstance().getLastSearchLatLng().latitude;
            lng = SearchParamManager.getInstance().getLastSearchLatLng().longitude;
        }else{
            lat = LocManager.getInstance().getLastLatLng().latitude;
            lng = LocManager.getInstance().getLastLatLng().longitude;
        }

        final Handler handler = new Handler();
        new ApiSaveSearch(this).request(name, lat, lng, typesCsv, featuresCsv, minFloorArea, maxFloorArea, minPlotArea, maxPlotArea, minPrice, maxPrice, minFloors, maxFloors, minRooms,
                minBaths, hasOpenHouse, hideForeclosure, hasContinent, hideNewConstruction, new Action() {
                    @Override
                    public void execute() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FilterSearchActivity.this, "Search Saved", Toast.LENGTH_SHORT).show();
                                applyFilters();
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
                                Toast.makeText(FilterSearchActivity.this, "Search Saved Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }

    private void applyFilters() {
        typesCsv = typesAdapter.getSelectedTypesIdCsv();
        featuresCsv = featuresAdapter.getPropertiesIdCsv();
        minFloorArea = mBinding.filterActRangeFloorSize.getMinimum();
        maxFloorArea = mBinding.filterActRangeFloorSize.getMaximum();
        minPlotArea = mBinding.filterActRangePlot.getMinimum();
        maxPlotArea = mBinding.filterActRangePlot.getMaximum();
        minPrice = mBinding.filterActRangePrice.getMinimum();
        maxPrice = mBinding.filterActRangePrice.getMaximum();
        minFloors = mBinding.filterActRangeFloors.getMinimum();
        maxFloors = mBinding.filterActRangeFloors.getMaximum();
        minRooms = mBinding.filterActRoomsCounter.getCounter();
        minBaths = mBinding.filterActBathCounter.getCounter();
        hasOpenHouse = mBinding.filterActOpenHousesSwitch.isChecked();
        hideForeclosure = mBinding.filterActForeclosureSwitch.isChecked();
        boolean hasContinent = mBinding.filterActContinentSwitch.isChecked();
        final boolean hideNewConstruction = mBinding.filterActNewConstructionSwitch.isChecked();
        SearchParamManager.getInstance().updateParams("property_type_ids", typesCsv);
        SearchParamManager.getInstance().updateParams("feature_ids", featuresCsv);
        SearchParamManager.getInstance().updateParams("min_floor_area", minFloorArea);
        SearchParamManager.getInstance().updateParams("max_floor_area", maxFloorArea);
        SearchParamManager.getInstance().updateParams("min_plot_area", minPlotArea);
        SearchParamManager.getInstance().updateParams("max_plot_area", maxPlotArea);
        SearchParamManager.getInstance().updateParams("min_price", minPrice);
        SearchParamManager.getInstance().updateParams("max_price", maxPrice);
        SearchParamManager.getInstance().updateParams("min_floor", minFloors);
        SearchParamManager.getInstance().updateParams("max_floor", maxFloors);
        SearchParamManager.getInstance().updateParams("min_rooms_count", minRooms);
        SearchParamManager.getInstance().updateParams("min_bathrooms_count", minBaths);
        SearchParamManager.getInstance().updateParams("has_open_house", hasOpenHouse);
        SearchParamManager.getInstance().updateParams("hide_foreclosures", hideForeclosure);
        SearchParamManager.getInstance().updateParams("hide_new_construction", hideNewConstruction);
    }

    private void resetAllFields() {
        typesAdapter.clearSelected();
        featuresAdapter.clearSelected();
        mBinding.filterActRangeFloorSize.clearRanges();
        mBinding.filterActRangePlot.clearRanges();
        mBinding.filterActRangePrice.clearRanges();
        mBinding.filterActRangeFloors.clearRanges();
        mBinding.filterActRoomsCounter.clearClickRange();
        mBinding.filterActBathCounter.clearClickRange();
        mBinding.filterActOpenHousesSwitch.setChecked(false);
        mBinding.filterActForeclosureSwitch.setChecked(false);
        mBinding.filterActContinentSwitch.setChecked(false);
        mBinding.filterActNewConstructionSwitch.setChecked(false);
    }

    private void initPropertyBtns() {
        List<PropertyType> propertyTypeList = MetaDataManager.getInstance().getPropertyTypes();
        typesAdapter = new PropertyTypeAdapter(this, propertyTypeList);
        mBinding.filterSearchActPropertyGrid.setAdapter(typesAdapter);
    }

    private void initExpandableLayout() {
        mBinding.filterActAdditionalFeatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.filterActAdditionalLayout.getVisibility() == View.VISIBLE) {
                    MyAnimationUtils.rotateRight(mBinding.filterActAdditionalFeatureImg);
                    MyAnimationUtils.collapse(mBinding.filterActAdditionalLayout);
                } else {
                    MyAnimationUtils.rotateLeft(mBinding.filterActAdditionalFeatureImg);
                    MyAnimationUtils.expand(mBinding.filterActAdditionalLayout);
                }
            }
        });

        mBinding.filterActSaveSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.filterActSaveLayout.getVisibility() == View.VISIBLE) {
                    MyAnimationUtils.collapse(mBinding.filterActSaveLayout);
                } else {
                    MyAnimationUtils.expand(mBinding.filterActSaveLayout);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.filterActScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 500);
                }
            }
        });
    }

    private void initAddFeaturesBtns() {
        List<IdLabel> propertyFeatures = MetaDataManager.getInstance().getPropertyFeatures();
        featuresAdapter = new PropertyAdditionalFeaturesAdapter(this, propertyFeatures);
        mBinding.filterSearchActAdditionalGridView.setAdapter(featuresAdapter);
    }



}

