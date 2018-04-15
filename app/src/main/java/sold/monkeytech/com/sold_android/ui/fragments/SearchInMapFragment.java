package sold.monkeytech.com.sold_android.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.CustomInfoWindowGoogleMap;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.Location;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiGetProperties;

public class SearchInMapFragment extends SupportMapFragment {

    private static final int POI_MARKER = 0;
    private static final int PROPERTY_MARKER = 1;
    GoogleMap map = null;
    Marker deviceLocationMarker = null;
    private TAction<LatLng> locationListener = null;
    private Marker mMarker;
    private HashMap<Marker, Property> myMarkers;
    private HashMap<Marker, POI> myPOIMarkers;
    private OnMapFragmentListener listener;
    private int type = -1;


    public interface OnMapFragmentListener {
        void onMarkerClick(Property property);

        void onMapTouch(int type);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnMapFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMapFragmentListener");
        }
    }

    public SearchInMapFragment setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View layout = super.onCreateView(layoutInflater, viewGroup, bundle);

        TouchableWrapper frameLayout = new TouchableWrapper(getActivity());
        frameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ((ViewGroup) layout).addView(frameLayout,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return layout;
    }

    public class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    listener.onMapTouch(type);
                    break;
                case MotionEvent.ACTION_UP:
                    listener.onMapTouch(type);
                    break;
            }
            return super.dispatchTouchEvent(event);
        }
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
                        if (myMarkers != null) {
                            HashMap<Marker, Property> tempHash = new HashMap<Marker, Property>();
                            tempHash.putAll(myMarkers);
                            Iterator it = tempHash.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
//                                System.out.println(pair.getKey() + " = " + pair.getValue());
                                if (marker.getPosition().latitude == ((Marker) pair.getKey()).getPosition().latitude) {
                                    Log.d("wow", "wow");
                                    listener.onMarkerClick((Property) pair.getValue());
                                }
                                it.remove(); // avoids a ConcurrentModificationException
                            }
                        }
                        if (!TextUtils.isEmpty(marker.getTitle())) {
                            marker.showInfoWindow();
                            return false;
                        } else {
                            return true;
                        }
                    }
                });

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        listener.onMarkerClick(null);
                    }
                });

                showLastSearch();
            }
        });
    }

    private void showLastSearch() {
        Location lastLocation = SearchParamManager.getInstance().getLastSearchLocation(); //return last or defualt search
        if (lastLocation != null) {
            final Handler handler = new Handler();

            new ApiGetProperties(getContext()).getNext(0, new TAction<List<Property>>() {
                @Override
                public void execute(final List<Property> properties) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (properties.size() > 0) {
                                LatLng latLng = new LatLng(properties.get(0).getDoubleLat(), properties.get(0).getDoubleLng());
                                animateCamera(latLng);
                                initPropertiesMarkers(properties);
                            }
                        }
                    });
                }
            }, null);
        }
    }

        private GoogleMap.CancelableCallback cameraAnimation = new GoogleMap.CancelableCallback() {
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

//    public void initPropertiesMarkers(LatLng latLng) {
//        SearchParamManager.getInstance().updateParams("lat", latLng.latitude);
//        SearchParamManager.getInstance().updateParams("lng", latLng.longitude);
//        final Handler handler = new Handler();
//        new ApiGetProperties(getContext()).getNext(0, new TAction<List<Property>>() {
//            @Override
//            public void execute(final List<Property> properties) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("wowInMap", "success");
//                        for (Property p : properties) {
//                            LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
//                            MarkerOptions markerOptions = new MarkerOptions().position(location)
//                                    .icon(getBitmapFromVectorDrawable(PROPERTY_MARKER, R.drawable.map_marker, p.getPrice().getShorted()));
//                            mMarker = map.addMarker(markerOptions);
//                            if(myMarkers == null)
//                                myMarkers = new HashMap<Marker, Property>();
//                            myMarkers.put(mMarker, p);
//                        }
//                    }
//                });
//            }
//        }, new TAction<String>() {
//            @Override
//            public void execute(String s) {
//
//            }
//        });
//    }

    public void initPropertiesMarkers(List<Property> properties) {
        for (Property p : properties) {
            LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
            MarkerOptions markerOptions = new MarkerOptions().position(location)
                    .icon(getBitmapFromVectorDrawable(PROPERTY_MARKER, R.drawable.map_marker, p.getPrice().getShorted()));
            mMarker = map.addMarker(markerOptions);
            if (myMarkers == null)
                myMarkers = new HashMap<Marker, Property>();
            myMarkers.put(mMarker, p);
        }
    }


    public void initPOIMarkers(Property property, List<POI> pois, GoogleMap map) {
        for (POI p : pois) {
            if (p != null && !TextUtils.isEmpty(p.getLat())) {
                LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
                MarkerOptions markerOptions = new MarkerOptions().position(location).title(p.getCategory().getName()).snippet(p.getName())
                        .icon(getBitmapFromVectorDrawable(POI_MARKER, R.drawable.poi_marker, p.getCategory().getColor()));

                Marker poiMarker = map.addMarker(markerOptions);
                String distance = LocManager.getInstance().getDistance(Float.parseFloat(property.getLat()), Float.parseFloat(property.getLng())
                        , Float.parseFloat(p.getLat()), Float.parseFloat(p.getLng()));
                poiMarker.setTag(distance);
                if (myPOIMarkers == null)
                    myPOIMarkers = new HashMap<Marker, POI>();
                myPOIMarkers.put(poiMarker, p);
                animateCamera(new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng())));
            }
        }
        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getContext());
        map.setInfoWindowAdapter(customInfoWindow);
    }

    public void initPOIHomeMarkers(Property property, GoogleMap map) {
        LatLng location = new LatLng(Double.parseDouble(property.getLat()), Double.parseDouble(property.getLng()));
        MarkerOptions markerOptions = new MarkerOptions().position(location)
                .icon(getBitmapFromVectorDrawable(2, R.drawable.poi_home_marker, ""));

        map.addMarker(markerOptions);
    }

    public void initPropertiesAroundMarkers(Property property, GoogleMap aroundMap) {
        if (property.getNearbyProperties() != null) {
            List<Property> nearbyProperties = property.getNearbyProperties();
            for (Property p : nearbyProperties) {
                LatLng location = new LatLng(Double.parseDouble(p.getLat()), Double.parseDouble(p.getLng()));
                MarkerOptions markerOptions = new MarkerOptions().position(location)
                        .icon(getBitmapFromVectorDrawable(PROPERTY_MARKER, R.drawable.map_marker, property.getPrice().getShorted()));

                mMarker = aroundMap.addMarker(markerOptions);
                if (myMarkers == null)
                    myMarkers = new HashMap<Marker, Property>();
                myMarkers.put(mMarker, p);
            }
            CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getContext());
            aroundMap.setInfoWindowAdapter(customInfoWindow);
        }
    }

    public static int convertToPixels(Context context, int nDP) {
        final float conversionScale = context.getResources().getDisplayMetrics().density;
        return (int) ((nDP * conversionScale) + 0.5f);
    }

    public BitmapDescriptor getBitmapFromVectorDrawable(int type, int drawableId, String text) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        if (type == PROPERTY_MARKER) {
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
        } else if (type == POI_MARKER) {
            Paint paint = new Paint();
            ColorFilter filter = new PorterDuffColorFilter(Color.parseColor(text), PorterDuff.Mode.SRC_IN);
            paint.setColorFilter(filter);

            Canvas canvas2 = new Canvas(bitmap);
            canvas2.drawBitmap(bitmap, 0, 0, paint);
        }

        return BitmapDescriptorFactory.fromBitmap(bitmap);
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

}