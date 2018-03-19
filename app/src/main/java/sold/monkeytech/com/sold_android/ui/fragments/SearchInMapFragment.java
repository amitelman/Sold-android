package sold.monkeytech.com.sold_android.ui.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetProperties;
import sold.monkeytech.com.sold_android.ui.activities.MainActivity;
import sold.monkeytech.com.sold_android.ui.activities.PreApprovedActivity;

public class SearchInMapFragment extends SupportMapFragment {

    GoogleMap map = null;
    Marker deviceLocationMarker = null;
    private TAction<LatLng> locationListener = null;
    private Marker mMarker;
    private HashMap<Marker, Property> myMarkers;
    private OnMapFragmentListener listener;

    public interface OnMapFragmentListener{
        void onMarkerClick(Property property);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity) {
            listener = (MainActivity) context;
        } else {
            throw new IllegalArgumentException("Containing activity must implement OnSearchListener interface");
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                map.getUiSettings().setZoomControlsEnabled(false);
                SearchInMapFragment.this.map = map;
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(myMarkers != null){
                            HashMap<Marker, Property> tempHash = new HashMap<Marker, Property>();
                            tempHash.putAll(myMarkers);
                            Iterator it = tempHash.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry)it.next();
//                                System.out.println(pair.getKey() + " = " + pair.getValue());
                                if(marker.getPosition().latitude == ((Marker)pair.getKey()).getPosition().latitude){
                                    Log.d("wow", "wow");
                                    listener.onMarkerClick((Property)pair.getValue());
                                }
                                it.remove(); // avoids a ConcurrentModificationException
                            }
                        }
                        return false;
                    }
                });

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        listener.onMarkerClick(null);
                    }
                });
            }
        });
    }

    public SearchInMapFragment setLocationListener(TAction<LatLng> listener) {
        this.locationListener = listener;
        return this;
    }

    public double getLat() {
        return deviceLocationMarker.getPosition().latitude;
    }

    public double getLon() {
        return deviceLocationMarker.getPosition().longitude;
    }

    public void setMarker(double lat, double lon) {
        deviceLocationMarker.setPosition(new LatLng(lat, lon));
    }


    public GoogleMap.CancelableCallback getCameraAnimation() {
        return cameraAnimation;
    }

    private GoogleMap.CancelableCallback cameraAnimation = new GoogleMap.CancelableCallback() {
        //
//        GoogleMap map = getMap();
        private LatLng latLng = (map == null) ? new LatLng(0, 0) : map.getCameraPosition().target;
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
        public void onCancel() {
        }

    };

    public void animateCamera(LatLng latLng) {
        if (map != null) {
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            map.animateCamera(location);
        }


    }

    public void getHouseProperties(LatLng latLng) {
        SearchParamManager.getInstance().updateParams("lat", latLng.latitude);
        SearchParamManager.getInstance().updateParams("lng", latLng.longitude);
        final Handler handler = new Handler();
        new ApiGetProperties(getContext()).getNext(0, new TAction<List<Property>>() {
            @Override
            public void execute(final List<Property> properties) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("wowInMap", "success");
                        for (Property p : properties) {
                            LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
                            MarkerOptions markerOptions = new MarkerOptions().position(location)
                                    .icon(getBitmapFromVectorDrawable(R.drawable.map_marker, p.getPrice().getShorted()));

                            mMarker = map.addMarker(markerOptions);
                            if(myMarkers == null)
                                myMarkers = new HashMap<Marker, Property>();
                            myMarkers.put(mMarker, p);
                        }
                    }
                });
            }
        }, new TAction<String>() {
            @Override
            public void execute(String s) {

            }
        });
    }


    public static int convertToPixels(Context context, int nDP){
        final float conversionScale = context.getResources().getDisplayMetrics().density;
        return (int) ((nDP * conversionScale) + 0.5f) ;
    }


    public BitmapDescriptor getBitmapFromVectorDrawable(int drawableId, String text) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Muli-Regular.ttf");

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(typeface);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(convertToPixels(getActivity(), 12));

        Rect textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);
        Log.d("wowMarker", "text on marker: " + text);
        if (textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
            paint.setTextSize(convertToPixels(getActivity(), 7));        //Scaling needs to be used for different dpi's

        //Calculate the positions
        int xTextPos = canvas.getWidth() / 2; //-2 is for regulating the x position offset

        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
        int yTextPos = convertToPixels(getActivity(), 18);

        canvas.drawText(text, xTextPos, yTextPos, paint);

        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}