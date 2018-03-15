package sold.monkeytech.com.sold_android.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSearchBinding;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.AutoComplete;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetProperties;
import sold.monkeytech.com.sold_android.ui.activities.SortFiltersActivity;
import sold.monkeytech.com.sold_android.ui.adapters.AutoCompleteAdapter;
import sold.monkeytech.com.sold_android.ui.custom_views.ClearableAutoCompleteTextView;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {


    private static final int LIST_FRAG = 0;
    private static final int MAP_FRAG = 1;
    private FragmentSearchBinding mBinding;
    private GoogleMap map;
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private int currentFragType = MAP_FRAG;
    private AutoCompleteAdapter autoCompleteAdapter;
    private Fragment currentFrag;


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
        initAutoComplete();


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

    private void initAutoComplete() {
        autoCompleteAdapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line);
        mBinding.searchFragAutoComplete.setAdapter(autoCompleteAdapter);
        mBinding.searchFragAutoComplete.setThreshold(0);

        mBinding.searchFragAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("wow", "wowclick" );
                AutoComplete selected = autoCompleteAdapter.getItemById(position);
                mBinding.searchFragAutoComplete.setText(selected.getName().toString());
                GeoDataClient mGeoDataClient = Places.getGeoDataClient(getContext(), null);

                mGeoDataClient.getPlaceById(selected.getPlaceId()).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                        if (task.isSuccessful()) {
                            PlaceBufferResponse places = task.getResult();
                            Place myPlace = places.get(0);
                            Log.i("wow", "Place found: " + myPlace.getName());
                            LatLng placeLocation = myPlace.getLatLng();
                            navigateToNewLocation(placeLocation);
                            places.release();
                        } else {
                            Log.e("wowAutoComplete", "Place not found.");
                        }
                    }
                });
            }
        });
    }

    private void navigateToNewLocation(LatLng placeLocation) {
        if(currentFragType == MAP_FRAG) {
            ((SearchInMapFragment) currentFrag).animateCamera(placeLocation);
            ((SearchInMapFragment) currentFrag).getHouseProperties(placeLocation);
        }
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
                if(currentFragType == MAP_FRAG)
                    loadList();
                else
                    loadMap();
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
        currentFragType = LIST_FRAG;
        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.map);
        mBinding.searchFragMyLocationBtn.setVisibility(View.GONE);
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchInListFragment listFragment = new SearchInListFragment();
        currentFrag = listFragment;
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
        currentFragType = MAP_FRAG;
        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.list);
        mBinding.searchFragMyLocationBtn.setVisibility(View.VISIBLE);
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchInMapFragment mapFragment = new SearchInMapFragment();
        currentFrag = mapFragment;
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
