package sold.monkeytech.com.sold_android.ui.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;

import java.util.HashMap;
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
import sold.monkeytech.com.sold_android.framework.serverapi.abs.params.BaseParam;
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
    Integer minFloorArea;
    Integer maxFloorArea;
    Integer minPlotArea;
    Integer maxPlotArea;
    Integer minPrice;
    Integer maxPrice;
    Integer minFloors;
    Integer maxFloors;
    Integer minRooms;
    Integer minBaths;
    Boolean hasOpenHouse;
    Boolean hideForeclosure;
    Boolean hasParking;
    Boolean hideNewConstruction;

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

        restorePreviousSettings();
    }

    private void restorePreviousSettings() {
        typesCsv = SearchParamManager.getInstance().getTypesCsv();
        typesAdapter.restoreLast(typesCsv);
        featuresCsv = SearchParamManager.getInstance().getFeaturesCsv();
        featuresAdapter.restoreLast(featuresCsv);
        minFloorArea = SearchParamManager.getInstance().getMinFloorArea();
        maxFloorArea = SearchParamManager.getInstance().getMaxFloorArea();
        minPlotArea = SearchParamManager.getInstance().getMinPlotArea();
        maxPlotArea = SearchParamManager.getInstance().getMaxPlotArea();
        minPrice = SearchParamManager.getInstance().getMinPrice();
        maxPrice = SearchParamManager.getInstance().getMaxPrice();
        minFloors = SearchParamManager.getInstance().getMinFloors();
        maxFloors = SearchParamManager.getInstance().getMaxFloors();
        minRooms = SearchParamManager.getInstance().getMinRooms();
        minBaths = SearchParamManager.getInstance().getMinBaths();
        hasOpenHouse = SearchParamManager.getInstance().isHasOpenHouse();
        hideForeclosure = SearchParamManager.getInstance().isHideForeclosure();
        hasParking = SearchParamManager.getInstance().isHasParking();
        hideNewConstruction = SearchParamManager.getInstance().isHideNewConstruction();

        mBinding.filterActRangeFloorSize.setMinimum(minFloorArea == null ? "" : minFloorArea + "");
        mBinding.filterActRangeFloorSize.setMaximum(maxFloorArea == null ? "" : maxFloorArea + "");
        mBinding.filterActRangePlot.setMinimum(minPlotArea == null ? "" : minPlotArea + "");
        mBinding.filterActRangePlot.setMaximum(maxPlotArea == null ? "" : maxPlotArea + "");
        mBinding.filterActRangePrice.setMinimum(minPrice == null ? "" : minPrice + "");
        mBinding.filterActRangePrice.setMaximum(maxPrice == null ? "" : maxPrice + "");
        mBinding.filterActRangeFloors.setMinimum(minFloors == null ? "" : minFloors + "");
        mBinding.filterActRangeFloors.setMaximum(maxFloors == null ? "" : maxFloors + "");
        mBinding.filterActRoomsCounter.setCounter(minRooms == null ? 0 : minRooms);
        mBinding.filterActBathCounter.setCounter(minBaths == null ? 0 : minBaths);
        mBinding.filterActOpenHousesSwitch.setChecked(hasOpenHouse == null ? false : hasOpenHouse);
        mBinding.filterActForeclosureSwitch.setChecked(hideForeclosure == null ? false : hideForeclosure);
        mBinding.filterActParkingSwitch.setChecked(hasParking == null ? false : hasParking);
        mBinding.filterActNewConstructionSwitch.setChecked(hideNewConstruction == null ? false : hideNewConstruction);
    }

    private void initBtns() {
        mBinding.filterActResetBtn.setOnClickListener(this);
        mBinding.filterActSaveSearchBtn.setOnClickListener(this);
        mBinding.filterActNameDeleteBtn.setOnClickListener(this);
        mBinding.filterActSaveBtn.setOnClickListener(this);
        mBinding.filterActApplyBtn.setOnClickListener(this);
        mBinding.filterActCancelBtn.setOnClickListener(this);

        mBinding.filterActNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mBinding.filterActNameDeleteBtn.setVisibility(View.VISIBLE);
                } else {
                    mBinding.filterActNameDeleteBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filterActResetBtn:
                resetAllFields();
                break;
            case R.id.filterActSaveSearchBtn:
                if (mBinding.filterActSaveLayout.getVisibility() == View.GONE) {
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
                if (!TextUtils.isEmpty(mBinding.filterActNameInput.getText().toString())) {
                    saveSearch();
                } else {
                    Toast.makeText(FilterSearchActivity.this, "Please set a title to search", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.filterActApplyBtn:
                setValues();
                applyFilters();
                finish();
                break;
            case R.id.filterActCancelBtn:
                finish();
                break;
        }
    }

    private void saveSearch() {
        double lat;
        double lng;
        String name = mBinding.filterActNameInput.getText().toString();
        if (SearchParamManager.getInstance().getLastSearchLatLng() != null) {
            lat = SearchParamManager.getInstance().getLastSearchLatLng().latitude;
            lng = SearchParamManager.getInstance().getLastSearchLatLng().longitude;
        } else {
            lat = LocManager.getInstance().getLastLatLng().latitude;
            lng = LocManager.getInstance().getLastLatLng().longitude;
        }
        setValues();
        final Handler handler = new Handler();
        new ApiSaveSearch(this).request(name, lat, lng, typesCsv, featuresCsv, minFloorArea, maxFloorArea, minPlotArea, maxPlotArea, minPrice, maxPrice, minFloors, maxFloors, minRooms,
                minBaths, hasOpenHouse, hideForeclosure, hasParking, hideNewConstruction, new Action() {
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

    public void setValues() {
        typesCsv = typesAdapter.getSelectedTypesIdCsv();
        featuresCsv = featuresAdapter.getFeaturesIdCsv();
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
        hasParking = mBinding.filterActParkingSwitch.isChecked();
        hideNewConstruction = mBinding.filterActNewConstructionSwitch.isChecked();
    }

    private void applyFilters() {
        SearchParamManager.getInstance().setTypesCsv(typesCsv);
        SearchParamManager.getInstance().setFeaturesCsv(featuresCsv);
        SearchParamManager.getInstance().setMinFloorArea(minFloorArea);
        SearchParamManager.getInstance().setMaxFloorArea(maxFloorArea);
        SearchParamManager.getInstance().setMinPlotArea(minPlotArea);
        SearchParamManager.getInstance().setMaxPlotArea(maxPlotArea);
        SearchParamManager.getInstance().setMinPrice(minPrice);
        SearchParamManager.getInstance().setMaxPrice(maxPrice);
        SearchParamManager.getInstance().setMinFloors(minFloors);
        SearchParamManager.getInstance().setMaxFloors(maxFloors);
        SearchParamManager.getInstance().setMinRooms(minRooms);
        SearchParamManager.getInstance().setMinBaths(minBaths);
        SearchParamManager.getInstance().setHasOpenHouse(hasOpenHouse);
        SearchParamManager.getInstance().setHideForeclosure(hideForeclosure);
        SearchParamManager.getInstance().setHasParking(hasParking);
        SearchParamManager.getInstance().setHideNewConstruction(hideNewConstruction);

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
        mBinding.filterActParkingSwitch.setChecked(false);
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

