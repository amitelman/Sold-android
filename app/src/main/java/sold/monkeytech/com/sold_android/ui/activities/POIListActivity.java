package sold.monkeytech.com.sold_android.ui.activities;

import android.Manifest;
import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

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
import java.util.HashMap;
import java.util.List;
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
import sold.monkeytech.com.sold_android.ui.adapters.expandable.ThreeLevelListAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.SearchInMapFragment;

public class POIListActivity extends BaseActivity implements SearchInMapFragment.OnMapFragmentListener {

    private static final float MAP_ZOOM = 10;
    private static final float POI_MAP_ZOOM = 13;
    public Property property;
    List<POI> pois;
    ThreeLevelListAdapter threeLevelListAdapterAdapter;
    private ActivityPoilistBinding mBinding;
    private GoogleMap map;
    private SearchInMapFragment mapFragment;
    private TAction<POI> onPoiClickAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_poilist);

        MapsInitializer.initialize(getApplicationContext());
        getProperty();


    }

    private void getProperty() {
        final Handler handler = new Handler();
        new ApiGetPropertyById(this).request(5, new TAction<Property>() {
            @Override
            public void execute(final Property property) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initListData(property);
                    }
                });
            }
        }, null);
    }

    private void initListData(Property property) {
        this.property = property;
        pois = property.getPoi();

        List<Category> tempCategories = new ArrayList<>();
        for (POI poi : pois) {
            tempCategories.add(poi.getCategory());
        }

        //parent level - categories
        List<Category> categories = POI.getSortedCategories(tempCategories);

        // second level - poi items
        List<List<POI>> poiItems = new ArrayList<>();

        for (Category c : categories) {
            long categoryId = c.getId();
            List<POI> tempPoi = new ArrayList<>();
            for (POI poi : pois) {
                if (categoryId == poi.getCategory().getId()) {
                    tempPoi.add(poi);
                }
            }
            poiItems.add(tempPoi);
            tempPoi = null;
        }

        // third level - poi Meta data
        List<HashMap<POI, List<Meta>>> thirdLevelData = new ArrayList<>();

        for (POI p : pois) {
            HashMap<POI, List<Meta>> oneItemMetaData = new HashMap<>();
//            oneItemMetaData.put(p, p.getMeta());
            oneItemMetaData.put(p, MetaDataManager.getInstance().getMetaDataMap());
            thirdLevelData.add(oneItemMetaData);
            oneItemMetaData = null;
        }

        threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, categories, poiItems, thirdLevelData, getOnPoiClickAction());
        tempCategories.clear();

        initUi();

    }

    private TAction<POI> getOnPoiClickAction() {
        return new TAction<POI>() {
            @Override
            public void execute(POI poi) {
                animateToLocation(Double.parseDouble(poi.getLat()), Double.parseDouble(poi.getLng()));
            }
        };
    }

    private void initUi() {
        mBinding.poiListActListView.setAdapter(threeLevelListAdapterAdapter);

        mBinding.poiListActListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                Log.d("wowPOI", "poi1");
                return false;
            }
        });

        // OPTIONAL : Show one list at a time
        mBinding.poiListActListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    mBinding.poiListActListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.poi_list_header, mBinding.poiListActListView, false);
        mBinding.poiListActListView.addHeaderView(header, null, false);
        loadMap(header);

        header.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        mBinding.poiListActListView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        mBinding.poiListActListView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        mBinding.poiListActListView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });
    }

    private void loadMap(ViewGroup header) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = new SearchInMapFragment();

        FrameLayout container = header.findViewById(R.id.poiListHeaderContainer);
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

//                LocManager.getInstance().getCurrentLatLng(new TAction<LatLng>() {
//                    @Override
//                    public void execute(LatLng latLng) {
//                        Log.d("wowLocation","onCurrentLocation");
//                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 7);
//                        map.animateCamera(cameraUpdate);
//                    }
//                });
                initMarkers();
            }
        });

    }

    private void initMarkers() {
        mapFragment.initPOIMarkers(property, pois, map);
        mapFragment.initPOIHomeMarkers(property, map);
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
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), POI_MAP_ZOOM);
                map.animateCamera(cameraUpdate);
            }
        }
    }

    @Override
    public void onMarkerClick(Property property) {
        Log.d("wow","onMarkerClick");
    }

    @Override
    public void onMapTouch() {
        Log.d("wow","onMapTouch");
        mBinding.poiListActListView.requestDisallowInterceptTouchEvent(true);
    }

}
