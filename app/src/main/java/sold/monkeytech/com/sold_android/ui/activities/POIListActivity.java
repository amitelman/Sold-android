package sold.monkeytech.com.sold_android.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityPoilistBinding;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.models.Category;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.ui.adapters.POIAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.POICategoryHeaderAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.expandable.ThreeLevelListAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.SearchInMapFragment;

public class POIListActivity extends BaseActivity implements SearchInMapFragment.OnMapFragmentListener {

    private static final float MAP_ZOOM = 11;
    private static final float POI_MAP_ZOOM = 10;
    public Property property;
    private List<POI> pois;
    private ActivityPoilistBinding mBinding;
    private GoogleMap map;
    private SearchInMapFragment mapFragment;
    private POICategoryHeaderAdapter categoryAdapter;
    private List<Category> categories;
    private HashMap<Category, List<POI>> categoryPoiList;
    private POIAdapter poiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_poilist);

        MapsInitializer.initialize(getApplicationContext());
        getProperty();


    }

    private void getProperty() {
        final Handler handler = new Handler();
        long id = getIntent().getLongExtra("propertyId", -1);
        new ApiGetPropertyById(this).request(id, new TAction<Property>() {
            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initUi();
                        initListData(property);
                    }
                });
            }
        }, null);
    }

    private void initUi() {
        mBinding.poiListActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListData(Property property) {
        this.property = property;
        pois = property.getPoi();

        List<Category> tempCategories = new ArrayList<>();
        for (POI poi : pois) {
            tempCategories.add(poi.getCategory());
        }

        //parent level - categories
        categories = POI.getSortedCategories(tempCategories);
        tempCategories.clear();

//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
//        layoutManager.setFlexWrap(FlexWrap.WRAP);
//        layoutManager.setAlignItems(AlignItems.BASELINE);
//        layoutManager.setFlexDirection(FlexDirection.ROW);
//        layoutManager.setJustifyContent(JustifyContent.CENTER);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mBinding.poiListActCategoryList.setLayoutManager(layoutManager);
        categoryAdapter = new POICategoryHeaderAdapter(this, categories, getOnCategoryClickAction());
        mBinding.poiListActCategoryList.setAdapter(categoryAdapter);

        poiAdapter = new POIAdapter(this, property, "",null, getOnPOIClickAction());
        mBinding.poiListActListView.setAdapter(poiAdapter);

//         second level - poi items
        categoryPoiList = new HashMap<>();

        for (Category c : categories) {
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

        loadMap();



    }

    private void setDefault(final List<POI> pois) {
        final int mIndexCount = mBinding.poiListActCategoryList.getAdapter().getItemCount()-1;
        Log.d("wowPOI","item count: " + mIndexCount);
        if(mIndexCount > -1){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBinding.poiListActCategoryList.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.poiCategoryItemBkg).performClick();
                    initMarkers(pois);
                }
            },100);
        }
    }

    private TAction<POI> getOnPOIClickAction() {
        return new TAction<POI>() {
            @Override
            public void execute(POI poi) {
                animateToLocation(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLng()));
            }
        };
    }

    private TAction<Category> getOnCategoryClickAction() {
        return new TAction<Category>() {
            @Override
            public void execute(Category category) {
                Iterator it = categoryPoiList.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry pair = (Map.Entry) it.next();
                    if(category.getId() == ((Category)pair.getKey()).getId()){
                        List<POI> poiList = new ArrayList<>();
                        poiList.add(null);
                        poiList.addAll((List<POI>) pair.getValue());
                        poiAdapter.updateItems(category.getName(), poiList);
                        initMarkers(poiList);
                    }
                }
            }
        };
    }

    private TAction<POI> getOnPoiClickAction() {
        return new TAction<POI>() {
            @Override
            public void execute(POI poi) {
                animateToLocation(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLng()));
            }
        };
    }


    private void loadMap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = new SearchInMapFragment();

        FrameLayout container = findViewById(R.id.poiListActContainer);
        fragmentTransaction.replace(container.getId(), mapFragment);
        fragmentTransaction.commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap","onMapReady");
                map = googleMap;
                if (ContextCompat.checkSelfPermission(POIListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if(map != null)
                        map.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(POIListActivity.this, PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                map.getUiSettings().setScrollGesturesEnabled(true);
                map.getUiSettings().setZoomGesturesEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(false);

                animateToMyLocation();

                //set property marker
                mapFragment.initPOIHomeMarkers(property, map);

//                LocManager.getInstance().getCurrentLatLng(new TAction<LatLng>() {
//                    @Override
//                    public void execute(LatLng latLng) {
//                        Log.d("wowLocation","onCurrentLocation");
//                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 7);
//                        map.animateCamera(cameraUpdate);
//                    }
//                });
//                initMarkers();
                setDefault(categoryPoiList.get(categories.get(0)));
            }
        });

    }

    private void initMarkers(List<POI> pois) {
        if(map != null){
            map.clear();
            mapFragment.initPOIHomeMarkers(property, map);
            mapFragment.initPOIMarkers(property, pois, map);
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if(!TextUtils.isEmpty(marker.getTitle())){
                        marker.showInfoWindow();
                        return false;
                    }else{
                        return true;
                    }
                }
            });
        }

    }

    private void animateToMyLocation() {
        if(PermissionUtils.checkPermissions(this, PermissionUtils.LOCATION_PERMISSIONS_1)){
            if(map != null){
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(property.getLat()), Double.parseDouble(property.getLng().toString())), MAP_ZOOM);
                map.animateCamera(cameraUpdate);
            }
        }
    }

    private void animateToLocation(double lat, double lng) {
        if(PermissionUtils.checkPermissions(this, PermissionUtils.LOCATION_PERMISSIONS_1)){
            if(map != null){
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 18);
                map.animateCamera(cameraUpdate);
            }
        }
    }

    @Override
    public void onMarkerClick(Property property) {
        Log.d("wow","onMarkerClick");
    }

    @Override
    public void onMapTouch(int type) {
        Log.d("wow","onMapTouch");
        mBinding.poiListActListView.requestDisallowInterceptTouchEvent(true);
    }

    public static void startWithProperty(Context context, Property property){
        Intent intent = new Intent(context, POIListActivity.class);
//        propertyStat = property;
        intent.putExtra("propertyId", property.getId());
        context.startActivity(intent);
    }

}
