package sold.monkeytech.com.sold_android.framework.managers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.DecimalFormat;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.monkeytechy.framework.interfaces.TAction;

import sold.monkeytech.com.sold_android.SoldApplication;

/**
 * Created by MonkeyFather on 05/03/2018.
 */

public class LocManager {

    public static LocManager instnace;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean isPassiveEnabled;
    private LatLng lastLatLng;

    public static synchronized LocManager getInstance() {
        if (instnace == null)
            instnace = new LocManager();
        return instnace;
    }


    private static String lastProvider;

    public void getCurrentLatLng(final TAction<LatLng> onGetLocationAction) {
        LocationManager locationManager = (LocationManager) SoldApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isPassiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
        if (isNetworkEnabled)
            lastProvider = LocationManager.NETWORK_PROVIDER;
        else if (isGPSEnabled)
            lastProvider = LocationManager.GPS_PROVIDER;
        if (lastProvider == null) {
            LatLng latLng = new LatLng(0, 0);
            if (onGetLocationAction != null) {
                onGetLocationAction.execute(latLng);
            }
            return;
        }
        final boolean[] didGetLocation = {false};
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                didGetLocation[0] = true;
                Log.d("onLocationChanged", "lat = " + location.getLatitude() + " , lan = " + location.getLongitude());
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                lastLatLng = latLng;
                if (onGetLocationAction != null) {
                    onGetLocationAction.execute(latLng);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("LocManager", "onStatusChanged");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("LocManager", "onProviderEnabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("LocManager", "onProviderDisabled");
            }
        };
        if (ActivityCompat.checkSelfPermission(SoldApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(SoldApplication.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("wowLocManger","Permission Error");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestSingleUpdate(lastProvider, locationListener, SoldApplication.getContext().getMainLooper());
    }

    public boolean isLocationEnabled(){
        LocationManager lm = (LocationManager) SoldApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        return (gpsEnabled || networkEnabled);
    }

    public LatLng getLastLatLng() {
        if (lastLatLng == null) {
            LocationManager lm = (LocationManager) SoldApplication.getContext().getSystemService(Context.LOCATION_SERVICE);
            isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            isPassiveEnabled = lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
            if (isNetworkEnabled)
                lastProvider = LocationManager.NETWORK_PROVIDER;
            else if (isGPSEnabled)
                lastProvider = LocationManager.GPS_PROVIDER;
            if (lastProvider!=null) {
                Location location = lm.getLastKnownLocation(lastProvider);
                lastLatLng = new LatLng(location.getLatitude(),location.getLongitude());
            }
        }
        return lastLatLng;
    }

    public String getDistance (float lat_a, float lng_a, float lat_b, float lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;
        double finalDistance = (distance * meterConversion)/1000;
        return String.valueOf(finalDistance/1000).format("%.2f", finalDistance) + "Km";
//        if(finalDistance > 1000)
//            return String.valueOf(finalDistance).format("%.2f", finalDistance) + "m";
//        else{
//        }
    }
}