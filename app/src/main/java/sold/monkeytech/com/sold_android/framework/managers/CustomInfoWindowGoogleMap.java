package sold.monkeytech.com.sold_android.framework.managers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import sold.monkeytech.com.sold_android.R;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.map_info_window, null);

        TextView title = view.findViewById(R.id.infoWindowTitle);
        TextView distance = view.findViewById(R.id.infoWindowDistance);
        TextView name = view.findViewById(R.id.infoWindowName);

        String distanceStr = (String) marker.getTag();
        title.setText(marker.getTitle());
        name.setText(marker.getSnippet());
        distance.setText(distanceStr);


        return view;
    }
}