package sold.monkeytech.com.sold_android.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityPropertyPageBinding;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.Category;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.TaxBracket;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiPinProperty;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiUnPinProperty;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseDaysAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseHoursAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.POICategoryHeaderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyFeaturesImagesAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.PropertyPageHeaderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.utils.ItemOffsetDecoration;
import sold.monkeytech.com.sold_android.ui.dialogs.TalkToAgentDialog;
import sold.monkeytech.com.sold_android.ui.fragments.SearchInMapFragment;

public class PropertyPageActivity extends BaseActivity implements SearchInMapFragment.OnMapFragmentListener, View.OnClickListener {

    private ActivityPropertyPageBinding mBinding;
    private PropertyPageHeaderAdapter pagerAdapter;
    private int dotscount;
    private ImageView[] dots;
    private OpenHouseHoursAdapter hoursAdapter;
    private boolean descriptionExpanded = false;
    private PropertyFeaturesImagesAdapter imagesAdapter;

    //POI
    private List<POI> pois;
    private ArrayList<Category> poiCategories;
    private HashMap<Category, List<POI>> categoryPoiList;
    private POICategoryHeaderAdapter categoryAdapter;

    public static Property propertyStat;
    private Property curProperty;

    private SearchInMapFragment poiMapFragment;
    private SearchInMapFragment aroundMapFragment;
    private SearchInMapFragment generalMapFragment;

    private GoogleMap poiMap;
    private GoogleMap aroundMap;
    private GoogleMap generalMap;
    private boolean generalMapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_property_page);

        getProperty(propertyStat);
//        initUi();
    }

    private void getProperty(final Property fullProperty) {
        final Handler handler = new Handler();
        mBinding.propertyActPb.show();
        new ApiGetPropertyById(this).request(propertyStat.getId(), new TAction<Property>() {
            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        curProperty = fullProperty;
                        propertyStat = null;
                        initUi();
                    }
                });
            }
        }, new Action() {
            @Override
            public void execute() {

            }
        });
    }

    private void initUi() {
//        curProperty = propertyStat;
//        propertyStat = null;

        initHeaderPager();
        initPropertyDetails(curProperty);
        initPropertyFeatures(curProperty);
        initPropertyPOIMap(curProperty);
        initCalculator();
        initDealsAround();
        initPropertiesAround(curProperty);
        initButtonsAndText();
        mBinding.propertyActPb.hide();
//        getProperty();
    }

    private void initCalculator() {

        String[] items = getResources().getStringArray(R.array.multiProperties);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        mBinding.propertyActCalcLayout.calcActRadioMultiSum.setAdapter(adapter);
        mBinding.propertyActCalcLayout.calcActRadioMultiSum.setSelection(adapter.getPosition("2"));

        mBinding.propertyActCalcLayout.calcActRadioSingle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculate();
            }
        });

        mBinding.propertyActCalcLayout.calcActRadioMulti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculate();
            }
        });

        calculate();

    }

    private void calculate(){
        List<TaxBracket> taxBrackets = MetaDataManager.getInstance().getTaxBrackets();
        Log.d("wowCalc","tax: " + taxBrackets);

        int propertyNum = mBinding.propertyActCalcLayout.calcActRadioSingle.isChecked() ? 1 : Integer.parseInt(mBinding.propertyActCalcLayout.calcActRadioMultiSum.getSelectedItem().toString());
//        Log.d("wowCalc","propertyPrice: " + propertyPrice + ", property num : " + propertyNum);

        int propertyPrice = curProperty.getPrice().getValue();
        int sunPrice = 0;
        double taxValue = 0;
        double percent = 0;


        for(TaxBracket tb : propertyNum == 1 ? MetaDataManager.getInstance().getSinglePropTax() : MetaDataManager.getInstance().getMultiPropTax()){
            if(propertyPrice > 0){
                if(tb.getMaxPrice() != null){
                    int valueRange = tb.getMaxPrice().getValue() - tb.getMinPrice().getValue();
                    if(propertyPrice >= valueRange){
                        taxValue += valueRange * tb.getTaxRate();
                        propertyPrice -= valueRange;
                    }else{
                        taxValue += propertyPrice * tb.getTaxRate();
                        propertyPrice = 0;
                    }
                }else{
                    taxValue += propertyPrice * tb.getTaxRate();
                    propertyPrice = 0;
                }
            }
        }
        percent += taxValue / curProperty.getPrice().getValue();
        mBinding.propertyActCalcLayout.calcActTaxPercent.setText(new DecimalFormat("##.##").format(percent) + "%");

    }

//    private void getProperty() {
//        final Handler handler = new Handler();
//        new ApiGetPropertyById(this).request(5, new TAction<Property>() {
//
//            @Override
//            public void execute(final Property property) {
//                curProperty = property;
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        initHeaderPager();
//                        initPropertyDetails(property);
//                        initPropertyFeatures(property);
//                        initPropertyPOIMap(property);
//                        initDealsAround();
//                        initPropertiesAround(property);
//                        initButtonsAndText(property);
//                    }
//                });
//            }
//        }, null);
//    }

    private void initPropertyDetails(Property property) {
        mBinding.propertyActTitle.setText(property.getFullAddress());
        mBinding.propertyActPrice.setText(property.getPrice().getFormatted());
        mBinding.propertyActAddress.setText(property.getFullAddress()); //todo: change this
        mBinding.propertyActRoomsCounter.setText(property.getRoomsCount() + "");
        mBinding.propertyActBathCounter.setText(property.getBathroomCount() + "");
        mBinding.propertyActSize.setText(property.getPlotArea() + " Sqm");
        mBinding.propertyActSqrm.setText(property.getFloorArea() + " Sqm");

        if(property.getOpenHouse().size() > 0){
            mBinding.propertyActOpenHouseLayout.setVisibility(View.VISIBLE);
            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.open_house_item_offset);
            mBinding.propertyActDaysRecyclerView.setAdapter(new OpenHouseDaysAdapter(property.getOpenHouse(), getOnDayClickAction(), false));
            mBinding.propertyActDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
            mBinding.propertyActDaysRecyclerView.addItemDecoration(itemDecoration);

            hoursAdapter = new OpenHouseHoursAdapter(null, getOnHourClickAction(), false);
            mBinding.propertyActHoursRecyclerView.setAdapter(hoursAdapter);
            mBinding.propertyActHoursRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
            mBinding.propertyActHoursRecyclerView.addItemDecoration(itemDecoration);
        }else{
            mBinding.propertyActOpenHouseLayout.setVisibility(View.GONE);
        }

        mBinding.propertyActStatus.setText(property.getPropertyStatus());
        mBinding.propertyActPricePSq.setText(property.getMeterPrice().getFormatted());
        mBinding.propertyActType.setText(property.getPropertyType().getName());
        mBinding.propertyActBuild.setText(property.getBuiltAt() + "");
        mBinding.propertyActParking.setText(property.getParkingSlot() + "");
        mBinding.propertyActTama.setText("???"); //todo : fill this
        mBinding.propertyActDescription.setText(property.getDescription());

        mBinding.propertyActReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = mBinding.propertyActDescription.getLayoutParams();
                if (!descriptionExpanded) {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    mBinding.propertyActDescription.setLayoutParams(params);
                    descriptionExpanded = true;
                } else {
                    params.height = TextUtils.dpToPx(70);
                    mBinding.propertyActDescription.setLayoutParams(params);
                    descriptionExpanded = false;
                    mBinding.propertyActDescription.setLines(4);
                }
            }
        });
    }

    private void initPropertyFeatures(Property property) {
        if (property.getPropertyFeatures().size() > 0) {
            mBinding.propertyPageActFeaturesLayout.setVisibility(View.VISIBLE);
            for (PropertyFeatures feature : property.getPropertyFeatures()) {
                View child = getLayoutInflater().inflate(R.layout.property_feature_item, null);
                TextView title = child.findViewById(R.id.featureItemTitle);
                TextView description = child.findViewById(R.id.featureItemValue);
                RecyclerView imagesView = child.findViewById(R.id.featureItemList);
                title.setText("• " + feature.getFeatureName());

                final SpannableStringBuilder descFinal = new SpannableStringBuilder("");
                final SpannableStringBuilder desc = new SpannableStringBuilder("");
                for (Meta m : feature.getMeta()) {
                    desc.append(m.getKey() + ": " + m.getValue() + ", ");

                    final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
                    desc.setSpan(bss, m.getKey().length() + 2, (m.getKey().length() + 2) + m.getValue().length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                    descFinal.append(desc);
                    desc.clear();
                }

                description.setText(descFinal.subSequence(0, descFinal.length()));

                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                imagesView.setLayoutManager(layoutManager);
                imagesAdapter = new PropertyFeaturesImagesAdapter(this, feature.getImages());
                imagesView.setAdapter(imagesAdapter);

                mBinding.propertyFeaturesActList.addView(child);
            }
        } else {
            mBinding.propertyPageActFeaturesLayout.setVisibility(View.GONE);
        }
    }

    //poi methods
    private void initPropertyPOIMap(final Property property) {
        if (property.getPoi() != null) {
            mBinding.propertyActPOILayout.setVisibility(View.VISIBLE);
            pois = property.getPoi();

            List<Category> tempCategories = new ArrayList<>();
            for (POI poi : pois) {
                tempCategories.add(poi.getCategory());
            }

            //parent level - categories
            poiCategories = POI.getSortedCategories(tempCategories);
            tempCategories.clear();

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            mBinding.propertyFeaturesActCategoryList.setLayoutManager(layoutManager);
            categoryAdapter = new POICategoryHeaderAdapter(this, poiCategories, getOnCategoryClickAction());
            mBinding.propertyFeaturesActCategoryList.setAdapter(categoryAdapter);

            //        poiAdapter = new POIAdapter(this, property, "", null, null);
            //        mBinding.propertyFeaturesActPoiListView.setAdapter(poiAdapter);

            //         second level - poi items
            categoryPoiList = new HashMap<>();

            for (Category c : poiCategories) {
                long categoryId = c.getId();
                List<POI> tempPoi = new ArrayList<>();
                for (POI poi : pois) {
                    if (categoryId == poi.getCategory().getId()) {
                        tempPoi.add(poi);
                    }
                }
                categoryPoiList.put(c, tempPoi);
                tempPoi = null;
            }

            loadPOIMap(property);

            mBinding.propertyActSeeMorePoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    POIListActivity.startWithProperty(PropertyPageActivity.this, property);
                }
            });
        } else {
            mBinding.propertyActPOILayout.setVisibility(View.GONE);
        }
    }

    private void loadPOIMap(final Property property) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        poiMapFragment = new SearchInMapFragment();

        FrameLayout container = findViewById(R.id.propertyFeaturesActPoiContainer);
        fragmentTransaction.replace(container.getId(), poiMapFragment);
        fragmentTransaction.commit();
        poiMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap", "onMapReady");
                poiMap = googleMap;
                if (ContextCompat.checkSelfPermission(PropertyPageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (poiMap != null)
                        poiMap.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(PropertyPageActivity.this, PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                poiMap.getUiSettings().setScrollGesturesEnabled(false);
                poiMap.getUiSettings().setZoomGesturesEnabled(false);
                poiMap.getUiSettings().setMyLocationButtonEnabled(false);

                animateToMyLocation(poiMap, property);

                //set property marker
                poiMapFragment.initPOIHomeMarkers(property, poiMap);

                if (poiCategories.size() > 0 && categoryPoiList.size() > 0)
                    setDefault(categoryPoiList.get(poiCategories.get(0)));
            }
        });
    }

    private void setDefault(final List<POI> pois) {
        final int mIndexCount = mBinding.propertyFeaturesActCategoryList.getAdapter().getItemCount() - 1;
        Log.d("wowPOI", "item count: " + mIndexCount);
        if (mIndexCount > -1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBinding.propertyFeaturesActCategoryList.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.poiCategoryItemBkg).performClick();
                    initPOIMarkers(pois);
                }
            }, 100);
        }
    }

    private void initPOIMarkers(List<POI> pois) {
        if (poiMap != null) {
            poiMap.clear();
            poiMapFragment.initPOIHomeMarkers(curProperty, poiMap);
            poiMapFragment.initPOIMarkers(curProperty, pois, poiMap);
            poiMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (!TextUtils.isEmpty(marker.getTitle())) {
                        marker.showInfoWindow();
                        return false;
                    } else {
                        return true;
                    }
                }
            });

        }
    }

    private void animateToMyLocation(GoogleMap map, Property property) {
        if (PermissionUtils.checkPermissions(this, PermissionUtils.LOCATION_PERMISSIONS_1)) {
            if (map != null) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(property.getLat()), Double.parseDouble(property.getLng().toString())), 12);
                map.animateCamera(cameraUpdate);
            }
        }
    }

    private TAction<Category> getOnCategoryClickAction() {
        return new TAction<Category>() {
            @Override
            public void execute(Category category) {
                Iterator it = categoryPoiList.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (category.getId() == ((Category) pair.getKey()).getId()) {
                        List<POI> poiList = new ArrayList<>();
                        poiList.add(null);
                        poiList.addAll((List<POI>) pair.getValue());
                        initPOIList(category, poiList);
                        initPOIMarkers(poiList);
                    }
                }
            }
        };
    }

    public void initPOIList(Category category, final List<POI> pois) {
        mBinding.propertyActPOIContainer.removeAllViews();
        for (int i = 0; i < pois.size(); i++) {
            if (i >= 4)
                return;
            View child = getLayoutInflater().inflate(R.layout.poi_item, null);
            RelativeLayout bkg = child.findViewById(R.id.poiItemBkg);
            TextView title = child.findViewById(R.id.poiItemTitle);
            TextView distance = child.findViewById(R.id.poiItemDistance);
            if (i == 0) {
                title.setText(category.getName() + " באזור");
                title.setTextColor(getResources().getColor(R.color.white));
                distance.setText("Distance");
                distance.setTextColor(getResources().getColor(R.color.white));
                bkg.setBackgroundColor(getResources().getColor(R.color.dark_grey_blue_two));
            } else {
                if (i % 2 == 0) {
                    bkg.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    bkg.setBackgroundColor(getResources().getColor(R.color.silver_two));
                }
                title.setText(pois.get(i).getName());
                if (!TextUtils.isEmpty(pois.get(i).getLat())) {
                    String distance2 = LocManager.getInstance().getDistance(Float.parseFloat(curProperty.getLat()), Float.parseFloat(curProperty.getLng()),
                            Float.parseFloat(pois.get(i).getLat()), Float.parseFloat(pois.get(i).getLng()));
                    distance.setText(distance2);
                }
                final int finalI = i;
                bkg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        poiMapFragment.animateCamera(new LatLng(Double.parseDouble(pois.get(finalI).getLat()), Double.parseDouble(pois.get(finalI).getLng())));
                    }
                });
            }
            mBinding.propertyActPOIContainer.addView(child);

        }
    }
    //poi methods done

    public void initDealsAround() {
        mBinding.propertyActHistoryContainer.removeAllViews();
        List<Property> nearbyDeals = curProperty.getNearbyProperties();
        if (nearbyDeals.size() > 0) {
            mBinding.propertyActHistoryLayout.setVisibility(View.VISIBLE);
            for (int i = -1; i < nearbyDeals.size(); i++) {
                if (i >= 4)
                    return;
                View child = getLayoutInflater().inflate(R.layout.poi_item, null);
                RelativeLayout bkg = child.findViewById(R.id.poiItemBkg);
                TextView title = child.findViewById(R.id.poiItemTitle);
                TextView distance = child.findViewById(R.id.poiItemDistance);
                if (i == -1) {
                    title.setText("כתובת");
                    title.setTextColor(getResources().getColor(R.color.white));
                    distance.setText("סכום");
                    distance.setTextColor(getResources().getColor(R.color.white));
                    bkg.setBackgroundColor(getResources().getColor(R.color.dark_grey_blue_two));
                } else {
                    if (i % 2 == 0) {
                        bkg.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        bkg.setBackgroundColor(getResources().getColor(R.color.silver_two));
                    }
                    title.setText(nearbyDeals.get(i).getFullAddress());
                    distance.setText(nearbyDeals.get(i).getPrice().getFormatted());
                }
                mBinding.propertyActHistoryContainer.addView(child);

                mBinding.propertyActSeeMoreHistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DealsHistoryActivity.startWithProperty(PropertyPageActivity.this, curProperty);
                    }
                });
            }
        }else{
            mBinding.propertyActHistoryLayout.setVisibility(View.GONE);
        }
    }

    public void initPropertiesAround(final Property property) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        aroundMapFragment = new SearchInMapFragment();

        FrameLayout container = findViewById(R.id.propertyActAroundContainer);
        fragmentTransaction.replace(container.getId(), aroundMapFragment);
        fragmentTransaction.commit();
        aroundMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap", "onMapReady");
                aroundMap = googleMap;
                if (ContextCompat.checkSelfPermission(PropertyPageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (aroundMap != null)
                        aroundMap.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(PropertyPageActivity.this, PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                aroundMap.getUiSettings().setScrollGesturesEnabled(false);
                aroundMap.getUiSettings().setZoomGesturesEnabled(false);
                aroundMap.getUiSettings().setMyLocationButtonEnabled(false);

                animateToMyLocation(aroundMap, property);

                //set property marker
                aroundMapFragment.initPOIHomeMarkers(property, aroundMap);
                aroundMapFragment.initPropertiesAroundMarkers(property, aroundMap);
            }
        });

        mBinding.propertyActSeeMoreAround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeAroundActivity.startWithProperty(PropertyPageActivity.this, property);
//                Intent intent = new Intent(PropertyPageActivity.this, HomeAroundActivity.class);
//                startActivity(intent);

            }
        });
    }

    private TAction<OpenHouse> getOnDayClickAction() {
        return new TAction<OpenHouse>() {
            @Override
            public void execute(OpenHouse openHouse) {
//                Toast.makeText(PropertyPageActivity.this, "wow", Toast.LENGTH_SHORT).show();
                hoursAdapter.updateItems(openHouse.getSlots());
                mBinding.propertyActRequestAShow.setAlpha(0.5f);
                mBinding.propertyActRequestAShow.setClickable(false);
            }
        };
    }

    private TAction<OpenHouseSlots> getOnHourClickAction() {
        return new TAction<OpenHouseSlots>() {
            @Override
            public void execute(OpenHouseSlots openHouseSlots) {
                if (openHouseSlots != null) {
                    mBinding.propertyActRequestAShow.setAlpha(1);
                    mBinding.propertyActRequestAShow.setClickable(true);
                } else {
                    mBinding.propertyActRequestAShow.setAlpha(0.5f);
                    mBinding.propertyActRequestAShow.setClickable(false);
                }
            }
        };
    }

    private void initHeaderPager() {
        List<String> headerItems = new ArrayList<>();
        boolean isWith3d = false;
        if (!TextUtils.isEmpty(curProperty.getVirtualTourCover())) {
            headerItems.add(curProperty.getVirtualTourCover());
            isWith3d = true;
        }

        if (curProperty.getAlbum() != null) {
            headerItems.addAll(curProperty.getAlbum());
        }
        pagerAdapter = new PropertyPageHeaderAdapter(this, headerItems, isWith3d, on3dTourClick());
        mBinding.propertyActViewPager.setAdapter(pagerAdapter);

        dotscount = pagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            mBinding.propertyActDotsLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

        mBinding.propertyActViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.propertyActHeaderTitle.setText(curProperty.getFullAddress());

        mBinding.propertyActAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("wowOffset", "" + verticalOffset);
                if (verticalOffset < -400) {
                    Log.d("wowOffset", "fadeOut");
                    MyAnimationUtils.fadeOut(mBinding.propertyActDotsLayout, null);
                    MyAnimationUtils.fadeOut(mBinding.propertyActBtnsLayout, null);
                    MyAnimationUtils.fadeIn(mBinding.propertyActHeaderTitle);
                } else if (verticalOffset > -400) {
                    Log.d("wowOffset", "fadeIn");
                    MyAnimationUtils.fadeOut(mBinding.propertyActHeaderTitle, new Action() {
                        @Override
                        public void execute() {
                            MyAnimationUtils.fadeIn(mBinding.propertyActDotsLayout);
                            MyAnimationUtils.fadeIn(mBinding.propertyActBtnsLayout);
                        }
                    });
                }
            }
        });

        mBinding.propertyActPhotosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.propertyActPhotosBtnLayout.setSelected(true);
                mBinding.propertyActPhotosBtn.setSelected(true);
                mBinding.propertyActMapViewBtnLayout.setSelected(false);
                mBinding.propertyActMapViewBtn.setSelected(false);
                mBinding.propertyActViewPager.setVisibility(View.VISIBLE);
                mBinding.propertyActGeneralMapContainer.setVisibility(View.GONE);
            }
        });
        mBinding.propertyActMapViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.propertyActPhotosBtnLayout.setSelected(false);
                mBinding.propertyActPhotosBtn.setSelected(false);
                mBinding.propertyActMapViewBtnLayout.setSelected(true);
                mBinding.propertyActMapViewBtn.setSelected(true);
                mBinding.propertyActViewPager.setVisibility(View.GONE);
                mBinding.propertyActGeneralMapContainer.setVisibility(View.VISIBLE);
                initGeneralMap();
            }
        });

        mBinding.propertyActPhotosBtnLayout.setSelected(true);
        mBinding.propertyActPhotosBtn.setSelected(true);
        mBinding.propertyActMapViewBtnLayout.setSelected(false);
        mBinding.propertyActMapViewBtn.setSelected(false);
    }

    private Action on3dTourClick() {
        return new Action() {
            @Override
            public void execute() {
                Sold3DTourActivity.startWithLink(PropertyPageActivity.this, curProperty.getVirtualTourLink());
            }
        };
    }

    private void initGeneralMap() {
        if (generalMapReady)
            return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        generalMapFragment = new SearchInMapFragment().setType(1);

        final FrameLayout container = findViewById(R.id.propertyActGeneralMapContainer);
        fragmentTransaction.replace(container.getId(), generalMapFragment);
        fragmentTransaction.commit();
        generalMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap", "onMapReady");
                generalMap = googleMap;
                if (ContextCompat.checkSelfPermission(PropertyPageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (generalMap != null)
                        generalMap.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(PropertyPageActivity.this, PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                generalMap.getUiSettings().setScrollGesturesEnabled(true);
                generalMap.getUiSettings().setZoomGesturesEnabled(false);
                generalMap.getUiSettings().setMyLocationButtonEnabled(false);

                animateToMyLocation(generalMap, curProperty);

                //set property marker
                generalMapFragment.initPOIHomeMarkers(curProperty, generalMap);
                generalMapReady = true;

            }
        });

//        mBinding.propertyActGeneralMapContainer.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d("wowMap","move");
//                        mBinding.propertyActScrollView.requestDisallowInterceptTouchEvent(true);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        Log.d("wowMap","upcancel");
//                        mBinding.propertyActScrollView.requestDisallowInterceptTouchEvent(false);
//                        break;
//                }
//                return container.onTouchEvent(event);
//            }
//        });


//        ((SearchInMapFragment) getSupportFragmentManager().findFragmentById(R.id.propertyActGeneralMapContainer)).setListener(new WorkaroundMapFragment.OnTouchListener() {
//            @Override
//            public void onTouch() {
//                mScrollView.requestDisallowInterceptTouchEvent(true);
//            }
//        });
    }

    private void initButtonsAndText() {
        mBinding.propertyActCrmId.setText("Property ID:" + curProperty.getCrmId() + getString(R.string.crm_id_text));
        mBinding.propertyActContactAgent.setOnClickListener(this);
        mBinding.propertyActRequestAShow.setOnClickListener(this);
        mBinding.propertyActWhatsappShare.setOnClickListener(this);
        mBinding.propertyActMailShare.setOnClickListener(this);
        mBinding.propertyActFacebookShare.setOnClickListener(this);
        mBinding.propertyActFavoriteBtn.setOnClickListener(this);
        mBinding.propertyActBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.propertyActContactAgent:
            case R.id.propertyActRequestAShow:
                if (!TextUtils.isEmpty(UserManager.getInstance().getInAppToken())) {
                    new TalkToAgentDialog(PropertyPageActivity.this, curProperty.getId().intValue(), 0, null).show();
                } else {
                    Intent intent = new Intent(PropertyPageActivity.this, TalkToAgentActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.propertyActWhatsappShare:
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(PropertyPageActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.propertyActMailShare:
                String mailTo = "";
                Intent email_intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailTo, null));
                email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                email_intent.putExtra(android.content.Intent.EXTRA_TEXT, "");

                startActivity(Intent.createChooser(email_intent, "Send email..."));
                break;

            case R.id.propertyActFacebookShare:
                ImageLoaderUtils.loadHighResPicture(curProperty.getCoverPhoto(), new TAction<Bitmap>() {
                    @Override
                    public void execute(Bitmap bitmap) {
                        SharePhoto photo = new SharePhoto.Builder()
                                .setBitmap(bitmap)
                                .build();
                        SharePhotoContent content = new SharePhotoContent.Builder()
                                .addPhoto(photo)
                                .build();
                        ShareDialog shareDialog = new ShareDialog(PropertyPageActivity.this);
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                });
                break;
            case R.id.propertyActFavoriteBtn:
                final Handler handler = new Handler();
                if (mBinding.propertyActFavoriteBtn.isSelected()) {
                    mBinding.propertyActFavoriteBtn.setSelected(false);
                    new ApiUnPinProperty(PropertyPageActivity.this).request(curProperty.getId(), null, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBinding.propertyActFavoriteBtn.setSelected(true);
                                }
                            });
                        }
                    });
                } else {
                    mBinding.propertyActFavoriteBtn.setSelected(true);
                    new ApiPinProperty(PropertyPageActivity.this).request(curProperty.getId(), null, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mBinding.propertyActFavoriteBtn.setSelected(false);
                                }
                            });

                        }
                    });
                }
                break;
            case R.id.propertyActShareBtn:

                break;
            case R.id.propertyActBackBtn:
                finish();
                break;
        }
    }

    @Override
    public void onMarkerClick(Property property) {

    }

    @Override
    public void onMapTouch(int type) {
        if (type == 1)
            mBinding.propertyActScrollView.requestDisallowInterceptTouchEvent(true);
    }

    public static void startWithProperty(Context context, Property property) {
        Intent intent = new Intent(context, PropertyPageActivity.class);
        propertyStat = property;
        context.startActivity(intent);
    }


}
