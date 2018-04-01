package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;

/**
 * Created by monkey on 25/06/2015.
 */
public class POIAdapter extends BaseAdapter {

    private String categoryName;
    private LayoutInflater inflater;
    private Context context;
    private Property property;
    private List<POI> pois;
    private TAction<POI> onPOIClick;

    public POIAdapter(Context context, Property property, String categoryName, List<POI> pois, TAction<POI> onPOIClick) {
        this.context = context;
        this.property = property;
        if (pois != null)
            this.pois = pois;
        this.onPOIClick = onPOIClick;
        this.categoryName = categoryName;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (pois != null)
            return pois.size();
        return 0;
    }

    @Override
    public POI getItem(int i) {
        if(i < 0)
            return null;
        return pois.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class BaseViewHolder {
        RelativeLayout bkg;
        TextView title;
        TextView distance;
    }

    public void updateItems(String name, List<POI> newPOI) {
        if (pois == null)
            pois = new ArrayList<>();
        pois.clear();
        pois.addAll(newPOI);
        this.categoryName = name;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.poi_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.poiItemBkg);
            baseViewHolder.title = convertView.findViewById(R.id.poiItemTitle);
            baseViewHolder.distance = convertView.findViewById(R.id.poiItemDistance);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final POI poi = getItem(position);

        if(poi == null){
            baseViewHolder.title.setText(categoryName + " באזור");
            baseViewHolder.title.setTextColor(context.getResources().getColor(R.color.white));
            baseViewHolder.distance.setText("Distance");
            baseViewHolder.distance.setTextColor(context.getResources().getColor(R.color.white));
            baseViewHolder.bkg.setBackgroundColor(context.getResources().getColor(R.color.dark_grey_blue_two));
        }else{
            if(position % 2 == 0){
                baseViewHolder.bkg.setBackgroundColor(context.getResources().getColor(R.color.white));
            }else{
                baseViewHolder.bkg.setBackgroundColor(context.getResources().getColor(R.color.silver_two));
            }
            baseViewHolder.title.setText(poi.getName());
            String distance = LocManager.getInstance().getDistance(Float.parseFloat(property.getLat()), Float.parseFloat(property.getLng()),
                    Float.parseFloat(poi.getLat()), Float.parseFloat(poi.getLng()));
            baseViewHolder.distance.setText(distance);
            baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


        return convertView;
    }


}


