package sold.monkeytech.com.sold_android.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSearchBinding;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.ui.activities.SortFiltersActivity;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {


    private FragmentSearchBinding mBinding;
    private GoogleMap map;
    private BottomSheetBehavior<View> mBottomSheetBehavior;

    boolean flag = false;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadMap();
        initUi();
        initBottomItem();

//        //todo: delete:
//        mBinding.searchFragListBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(flag == false)
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                else{
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }
//                flag = !flag;
//            }
//        });
    }

    private void initUi() {
        mBinding.searchFragMyLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateToMyLocation();
            }
        });

        mBinding.searchFragSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadList();
            }
        });

        mBinding.searchFragFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), SortFiltersActivity.class);
//                startActivity(intent);
            }
        });

        mBinding.searchFragSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SortFiltersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadList() {
//        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.map);
        mBinding.searchFragMyLocationBtn.setVisibility(View.GONE);
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchInListFragment listFragment = new SearchInListFragment();
        fragmentTransaction.replace(R.id.searchFragContainer, listFragment);
        fragmentTransaction.commit();
    }

    private void initBottomItem() {
        View bottomSheet = mBinding.searchFragBottomItem;
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void loadMap() {
        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.list);
        mBinding.searchFragMyLocationBtn.setVisibility(View.VISIBLE);
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchInMapFragment mapFragment = new SearchInMapFragment();
        fragmentTransaction.replace(R.id.searchFragContainer, mapFragment);
        fragmentTransaction.commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                Log.d("wowMap","onMapReady");
                map = googleMap;
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    if(map != null)
                        map.setMyLocationEnabled(true);
                    Log.d("wowMap", "PERMISSION_GRANTED");
                } else {
                    Log.d("wowMap", "PERMISSION_NOT_GRANTED");
                    PermissionUtils.askPermissions(getActivity(), PermissionUtils.LOCATION_PERMISSIONS_1, PermissionUtils.PERMISSION_LOCATION_1_REQUEST_CODE, getString(R.string.perm_location_rationale));
                }

                map.getUiSettings().setScrollGesturesEnabled(true);
                map.getUiSettings().setZoomGesturesEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(false);

                animateToMyLocation();

                LocManager.getInstance().getCurrentLatLng(new TAction<LatLng>() {
                    @Override
                    public void execute(LatLng latLng) {
                        Log.d("wowLocation","onCurrentLocation");
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 7);
                        map.animateCamera(cameraUpdate);
                    }
                });

//                Branch defaultBranch = branches.get(0);
//                addBranchMarkers(defaultBranch);
//                animateCameraWithOffset(defaultBranch.getLatLng(), true);

            }
        });
    }

    private void animateToMyLocation() {
        if(PermissionUtils.checkPermissions(getActivity(), PermissionUtils.LOCATION_PERMISSIONS_1)){
            if(map != null){
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LocManager.getInstance().getLastLatLng(), 7);
                map.animateCamera(cameraUpdate);
            }
        }
    }

//    private void addBranchMarkers(Branch branch) {
//        if(branch != null){
//            map.addMarker(new MarkerOptions()
//                    .position(branch.getLatLng())
//                    .title(branch.getTitle())
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_location)));
//            Log.d("wowMap","added Branch Markers at: " + branch.getTitle());
//        }
//    }
}
