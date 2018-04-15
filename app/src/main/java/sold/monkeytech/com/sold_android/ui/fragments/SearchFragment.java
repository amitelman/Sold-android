package sold.monkeytech.com.sold_android.ui.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSearchBinding;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.Utils.PermissionUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.AutoComplete;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetProperties;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetPropertyById;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiPinProperty;
import sold.monkeytech.com.sold_android.ui.activities.FilterSearchActivity;
import sold.monkeytech.com.sold_android.ui.activities.PropertyPageActivity;
import sold.monkeytech.com.sold_android.ui.activities.SearchLocationActivity;
import sold.monkeytech.com.sold_android.ui.activities.SortFiltersActivity;
import sold.monkeytech.com.sold_android.ui.adapters.AutoCompleteAdapter;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements SearchInMapFragment.OnMapFragmentListener{


    private static final int LIST_FRAG = 0;
    private static final int MAP_FRAG = 1;
    private static final int MY_LOCATION = 0;
    private static final int NEW_LOCATION = 1;
    private static final int FILTER_INTENT = 100;
    private FragmentSearchBinding mBinding;
    private GoogleMap map;
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private int currentFragType = MAP_FRAG;
    private AutoCompleteAdapter autoCompleteAdapter;
    private Fragment currentFrag;
    private LatLng previousSearch;


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

        if(currentFragType == MAP_FRAG){
            loadMap();
        }else{
            loadList();
        }
        initUi();
        initBottomItem();
        initAutoComplete();


    }

    private void initAutoComplete() {
        mBinding.searchFragAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchLocationActivity.class);
                intent.putExtra("type", SearchLocationActivity.CHOOSE_BOTH);
                startActivityForResult(intent, SearchLocationActivity.CHOOSE_BOTH);
            }
        });
        return;
//        autoCompleteAdapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line);
//        mBinding.searchFragAutoComplete.setAdapter(autoCompleteAdapter);
//        mBinding.searchFragAutoComplete.setThreshold(0);
//
//        mBinding.searchFragAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
//                Log.d("wow", "wowclick" );
//                AutoComplete selected = autoCompleteAdapter.getItemById(position);
//                mBinding.searchFragAutoComplete.setText(selected.getName().toString());
//                GeoDataClient mGeoDataClient = Places.getGeoDataClient(getContext(), null);
//                mGeoDataClient.getPlaceById(selected.getPlaceId()).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
//                    @Override
//                    public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
//                        if (task.isSuccessful()) {
//                            PlaceBufferResponse places = task.getResult();
//                            Place myPlace = places.get(0);
//                            Log.i("wow", "Place found: " + myPlace.getName());
//                            final LatLng placeLocation = myPlace.getLatLng();
//                            SearchParamManager.getInstance().setLastSearchLatLng(placeLocation);
////                            previousSearch = placeLocation;
//                            places.release();
//                            animateToLocation(placeLocation);
//                        } else {
//                            Log.e("wowAutoComplete", "Place not found.");
//                        }
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == SearchLocationActivity.CHOOSE_BOTH){
                long id = data.getLongExtra("id", -1);
                String name = data.getStringExtra("name");
                String type = data.getStringExtra("type");
                Location location = new Location(id, name, type);
                Log.d("wow","location: " + id + " - " + name + "/" + type);
                SearchParamManager.getInstance().updateParams("location_id", location.getId());
                SearchParamManager.getInstance().updateParams("location_type", location.getLocationType());
                SearchParamManager.getInstance().setLastSearchLocation(location);

                final Handler handler = new Handler();
                new ApiGetProperties(getContext()).getNext(0, new TAction<List<Property>>() {
                    @Override
                    public void execute(final List<Property> properties) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mBinding.searchFragCounter.setText("Showing " + properties.size() + " Home" + (properties.size() > 0 ? "s" : ""));
                                Log.d("wowSearch", "success list of properties: " + properties.size());
                                if(currentFragType == MAP_FRAG){
                                    if(properties.size() > 0){
                                        LatLng latLng = new LatLng(properties.get(0).getDoubleLat(), properties.get(0).getDoubleLng());
                                        ((SearchInMapFragment) currentFrag).animateCamera(latLng);
                                        ((SearchInMapFragment) currentFrag).initPropertiesMarkers(properties);
                                    }
                                }else{
                                    if(properties.size() > 0){
//                                        ((SearchInListFragment) currentFrag).refreshSearch(properties);
                                    }
                                }
//                                for (Property p : properties) {
//                                    LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
//                                    MarkerOptions markerOptions = new MarkerOptions().position(location)
//                                            .icon(getBitmapFromVectorDrawable(PROPERTY_MARKER, R.drawable.map_marker, p.getPrice().getShorted()));
//                                    mMarker = map.addMarker(markerOptions);
//                                    if(myMarkers == null)
//                                        myMarkers = new HashMap<Marker, Property>();
//                                    myMarkers.put(mMarker, p);
//                                }
                            }
                        });
                    }
                }, new TAction<String>() {
                    @Override
                    public void execute(String s) {

                    }
                });
//                animateToLocation(placeLocation);
            }
        }
    }

    private void initUi() {
//        mBinding.searchFragMyLocationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateToLocation(LocManager.getInstance().getLastLatLng());
//            }
//        });

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
                Intent intent = new Intent(getActivity(), FilterSearchActivity.class);
                startActivity(intent);
            }
        });

        mBinding.searchFragSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SortFiltersActivity.class);
                startActivityForResult(intent, FILTER_INTENT);
            }
        });

    }


    private void loadList() {
        currentFragType = LIST_FRAG;
        mBinding.searchFragSortBtn.setVisibility(View.VISIBLE);
        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.map);
//        mBinding.searchFragMyLocationBtn.setVisibility(View.GONE);
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

    public void setBottomItemAndShow(final Property property){
        if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
            final View bottomSheet = mBinding.searchFragBottomItemLayout;
            ;
            ImageLoaderUtils.loadBigPictureImage(property.getCoverPhoto(), (ImageView)bottomSheet.findViewById(R.id.searchItemBkg), null);
            ((TextView)bottomSheet.findViewById(R.id.searchItemPrice)).setText(property.getPrice().getFormatted());
            //todo: locale title\address parser
            ((TextView)bottomSheet.findViewById(R.id.searchItemTitle)).setText(property.getAddress().getStreetName() + property.getHouseNumber() + " Street");
            ((TextView)bottomSheet.findViewById(R.id.searchItemAddress)).setText(property.getAddress().getCityName());
            ((TextView)bottomSheet.findViewById(R.id.searchItemRoomsCounter)).setText(property.getRoomsCount() + "");
            ((TextView)bottomSheet.findViewById(R.id.searchItemBathCounter)).setText(property.getBathroomCount() + "");
            ((TextView)bottomSheet.findViewById(R.id.searchItemSize)).setText(property.getFloorArea() + "");
            ((TextView)bottomSheet.findViewById(R.id.searchItemSqrm)).setText(property.getPlotArea() + "");
            ((ImageButton)bottomSheet.findViewById(R.id.searchItemFavorite)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((TextView)bottomSheet.findViewById(R.id.searchItemSqrm)).setSelected(true);
                    final Handler handler = new Handler();
                    new ApiPinProperty(getContext()).request(property.getId(), null, new Action() {
                        @Override
                        public void execute() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((TextView)bottomSheet.findViewById(R.id.searchItemSqrm)).setSelected(false);
                                }
                            });

                        }
                    });
                }
            });

            bottomSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PropertyPageActivity.startWithProperty(getContext(), property);
                }
            });

            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }else{
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


    }

    public void hideBottomItem() {
        if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED)
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void loadMap() {
        currentFragType = MAP_FRAG;
        mBinding.searchFragSortBtn.setVisibility(View.GONE);
        mBinding.searchFragSwitchBtn.setImageResource(R.drawable.list);
//        mBinding.searchFragMyLocationBtn.setVisibility(View.VISIBLE);
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

                //todo: restore this
//                animateToLocation(null);

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

//    private void animateToLocation(LatLng latLng) {
//        if(latLng != null){
//            navigateToNewLocation(latLng);
//            return;
//        }
//        if(latLng == null && SearchParamManager.getInstance().getLastSearchLatLng() != null){
//            navigateToNewLocation(SearchParamManager.getInstance().getLastSearchLatLng());
//            return;
//        }
//        if(PermissionUtils.checkPermissions(getActivity(), PermissionUtils.LOCATION_PERMISSIONS_1)){
//            if(map != null){
//                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(LocManager.getInstance().getLastLatLng(), 7);
//                map.animateCamera(cameraUpdate);
//                if(currentFragType == MAP_FRAG && map != null)
//                    ((SearchInMapFragment) currentFrag).initPropertiesMarkers(LocManager.getInstance().getLastLatLng());
//            }
//        }
//
//    }

//    private void navigateToNewLocation(LatLng placeLocation) {
//        if(currentFragType == MAP_FRAG && map != null) {
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(placeLocation, 12);
//            map.animateCamera(cameraUpdate);
//            ((SearchInMapFragment) currentFrag).initPropertiesMarkers(placeLocation);
//        }else if(currentFragType == LIST_FRAG){
//            ((SearchInListFragment) currentFrag).refreshSearch(placeLocation);
//        }
//    }


    @Override
    public void onMarkerClick(Property property) {
        //if this doesnt work,, remove comment in mainActivity
        if (property != null) {
            ((SearchFragment) currentFrag).setBottomItemAndShow(property);
        }else{
            ((SearchFragment) currentFrag).hideBottomItem();
        }

    }

    @Override
    public void onMapTouch(int type) {

    }

    public void backPressed() {
        if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
