package sold.monkeytech.com.sold_android.ui.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.monkeytechy.framework.interfaces.TAction;

public class StaticMapFragment extends SupportMapFragment {

    GoogleMap map=null;
    Marker deviceLocationMarker=null;
    private TAction<LatLng> locationListener=null;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
        this.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                map.getUiSettings().setZoomControlsEnabled(false);
                StaticMapFragment.this.map = map;
            }
        });
//        GoogleMap map = this.getMap();
//        if(map==null)return;
//        map.getUiSettings().setZoomControlsEnabled(false);
    }

    public StaticMapFragment setLocationListener(TAction<LatLng> listener){
        this.locationListener=listener;
        return this;
    }
    public double getLat(){
        return deviceLocationMarker.getPosition().latitude;
    }
    public double getLon(){
        return deviceLocationMarker.getPosition().longitude;
    }
    public void setMarker(double lat,double lon){
        deviceLocationMarker.setPosition(new LatLng(lat, lon));
    }


    public GoogleMap.CancelableCallback getCameraAnimation() {
        return cameraAnimation;
    }

    private GoogleMap.CancelableCallback cameraAnimation = new GoogleMap.CancelableCallback(){
//
//        GoogleMap map = getMap();
        private LatLng latLng=(map==null)?new LatLng(0,0):map.getCameraPosition().target;
        //initial zoom
        static final int initZoom = 13;
        //steps the zoom
        int stepZoom = 0;
        // number of steps in zoom, be careful with this number!
        int stepZoomMax = 15;
        //number of .zoom steps in a step
        int stepZoomDetent = (18 - initZoom) / stepZoomMax;
        //when topause zoom for spin
        int stepToSpin = 4;
        //steps the spin
        int stepSpin = 0;
        //number of steps in spin (factor of 360)
        int stepSpinMax = 4;
        //number of degrees in stepSpin
        int stepSpinDetent = 360 / stepSpinMax;
        final int mapHopDelay = 2000;
        @Override
        public void onFinish() {
        }
        @Override
        public void onCancel()
        {}

    };


}