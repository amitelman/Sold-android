package sold.monkeytech.com.sold_android.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityHomeAroundBinding;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiPinProperty;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiUnPinProperty;
import sold.monkeytech.com.sold_android.ui.fragments.SearchFragment;
import sold.monkeytech.com.sold_android.ui.fragments.SearchInMapFragment;

import static sold.monkeytech.com.sold_android.SoldApplication.getContext;


public class HomeAroundActivity extends BaseActivity implements SearchInMapFragment.OnMapFragmentListener {

    private static Property propertyStat;
    private Property property;
    private ActivityHomeAroundBinding mBinding;
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private SearchInMapFragment mapFragment;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_around);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_around);

        property = propertyStat;
        propertyStat = null;

        initUi();
        initBottomItem();
        setBottomItemAndShow(property);
        hideBottomItem();
        loadMap();
    }

    private void initUi() {
        mBinding.homeAroundActTitle.setText("Home around " + property.getFullAddress());
        mBinding.homeAroundActBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initBottomItem() {
        View bottomSheet = mBinding.homeAroundActBottomItem;
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void setBottomItemAndShow(final Property property) {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            final View bottomSheet = mBinding.homeAroundActBottomItemLayout;
            if (property.getCoverPhoto() != null) {
                ImageLoaderUtils.loadBigPictureImage(property.getCoverPhoto(), (ImageView) bottomSheet.findViewById(R.id.searchItemBkg), null);
            }
            ((TextView) bottomSheet.findViewById(R.id.searchItemPrice)).setText(property.getPrice().getFormatted());
            //todo: locale title\address parser
            ((TextView) bottomSheet.findViewById(R.id.searchItemTitle)).setText(property.getAddress().getStreetName() + property.getHouseNumber() + " Street");
            ((TextView) bottomSheet.findViewById(R.id.searchItemAddress)).setText(property.getAddress().getCityName());
            ((TextView) bottomSheet.findViewById(R.id.searchItemRoomsCounter)).setText(property.getRoomsCount() + "");
            ((TextView) bottomSheet.findViewById(R.id.searchItemBathCounter)).setText(property.getBathroomCount() + "");
            ((TextView) bottomSheet.findViewById(R.id.searchItemSize)).setText(property.getFloorArea() + "");
            ((TextView) bottomSheet.findViewById(R.id.searchItemSqrm)).setText(property.getPlotArea() + "");
            ((ImageButton) bottomSheet.findViewById(R.id.searchItemFavorite)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Handler handler = new Handler();
                    if (v.isSelected()) {
                        v.setSelected(false);
                        new ApiUnPinProperty(HomeAroundActivity.this).request(property.getId(), null, new Action() {
                            @Override
                            public void execute() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((ImageButton) bottomSheet.findViewById(R.id.searchItemFavorite)).setSelected(true);
                                    }
                                });
                            }
                        });
                    } else {
                        ((ImageButton) bottomSheet.findViewById(R.id.searchItemFavorite)).setSelected(true);
                        new ApiPinProperty(HomeAroundActivity.this).request(property.getId(), null, new Action() {
                            @Override
                            public void execute() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((ImageButton) bottomSheet.findViewById(R.id.searchItemFavorite)).setSelected(false);
                                    }
                                });

                            }
                        });
                    }
                }
            });

            bottomSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PropertyPageActivity.startWithProperty(HomeAroundActivity.this, property);
                }
            });

//            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        } else {
//            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        }

        }
    }

    public void hideBottomItem() {
        if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void loadMap() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mapFragment = new SearchInMapFragment();
        fragmentTransaction.replace(R.id.homeAroundActContainer, mapFragment);
        fragmentTransaction.commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap", "onMapReady");
                map = googleMap;
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (map != null)
                        map.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(HomeAroundActivity.this, PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                map.getUiSettings().setScrollGesturesEnabled(true);
                map.getUiSettings().setZoomGesturesEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(false);

                animateToLocation(new LatLng(Double.parseDouble(property.getLat()), Double.parseDouble(property.getLng())));
                mapFragment.initPropertiesAroundMarkers(property, map);

//                LocManager.getInstance().getCurrentLatLng(new TAction<LatLng>() {
//                    @Override
//                    public void execute(LatLng latLng) {
//                        Log.d("wowLocation","onCurrentLocation");
//                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 7);
//                        map.animateCamera(cameraUpdate);
//                    }
//                });

//                Branch defaultBranch = branches.get(0);
//                addBranchMarkers(defaultBranch);
//                animateCameraWithOffset(defaultBranch.getLatLng(), true);

            }
        });
    }

    private void animateToLocation(LatLng latLng) {
        if (PermissionUtils.checkPermissions(this, PermissionUtils.LOCATION_PERMISSIONS_1)) {
            if (map != null) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12);
                map.animateCamera(cameraUpdate);
            }
        }
    }

    @Override
    public void onMarkerClick(final Property property) {
        if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setBottomItemAndShow(property);
                }
            }, 500);
        } else {
            if (property != null) {
                setBottomItemAndShow(property);
            } else {
                hideBottomItem();
            }
        }

    }

    @Override
    public void onMapTouch(int type) {

    }

    public static void startWithProperty(Context context, Property property) {
        Intent intent = new Intent(context, HomeAroundActivity.class);
        propertyStat = property;
        context.startActivity(intent);

    }
}
