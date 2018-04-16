package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;
import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiPinProperty;
import sold.monkeytech.com.sold_android.framework.serverapi.property.ApiUnPinProperty;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiGetFavorites;
import sold.monkeytech.com.sold_android.pagination.abs.PagibaleAdapter;
import sold.monkeytech.com.sold_android.ui.activities.PropertyPageActivity;

/**
 * Created by monkey on 25/06/2015.
 */
public class SearchInListAdapter extends BaseAdapter implements PagibaleAdapter<List<Property>> {
    private Context context;
    private List<Property> properties;
    private LayoutInflater inflater;

    List<Property> favProp;

    public SearchInListAdapter(Context context, List<Property> properties) {
        this.context = context;
        if(properties != null)
            this.properties = properties;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void updateList(List<Property> properties){
        if(this.properties == null)
            this.properties = new ArrayList<>();
        this.properties.clear();
        if(properties != null)
            this.properties.addAll(properties);
        notifyDataSetChanged();
    }

    public void updateFavList(List<Property> properties){
        if(this.favProp == null)
            this.favProp = new ArrayList<>();
        this.favProp.clear();
        if(properties != null)
            this.favProp.addAll(properties);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if(properties != null)
            return properties.size();
        return 0;
    }

    @Override
    public Property getItem(int i) {
        return properties.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void addItems(List<Property> items) {
        Log.d("wowPegination", "Loading " + items.size() + " more item");
        Toast.makeText(context, "Loading More!", Toast.LENGTH_SHORT).show();
        if(properties == null)
            properties = new ArrayList<>();
        properties.addAll(items);
        notifyDataSetChanged();
    }

    public void clearList() {
        if(properties != null)
            properties.clear();
        notifyDataSetChanged();
    }


    public static class BaseViewHolder{
        ImageView bkg;
        TextView price;
        ImageButton favBtn;
        TextView title;
        TextView address;
        TextView roomsCounter;
        TextView bathCounter;
        TextView size;
        TextView sqrm;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null){
            convertView = inflater.inflate(R.layout.sold_search_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.searchItemBkg);
            baseViewHolder.price = convertView.findViewById(R.id.searchItemPrice);
            baseViewHolder.favBtn = convertView.findViewById(R.id.searchItemFavorite);
            baseViewHolder.title = convertView.findViewById(R.id.searchItemTitle);
            baseViewHolder.address = convertView.findViewById(R.id.searchItemAddress);
            baseViewHolder.roomsCounter = convertView.findViewById(R.id.searchItemRoomsCounter);
            baseViewHolder.bathCounter = convertView.findViewById(R.id.searchItemBathCounter);
            baseViewHolder.size = convertView.findViewById(R.id.searchItemSize);
            baseViewHolder.sqrm = convertView.findViewById(R.id.searchItemSqrm);

            convertView.setTag(baseViewHolder);
        }else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }


        final Property property = getItem(position);

        baseViewHolder.bkg.setImageResource(0);
        ImageLoaderUtils.loadBigPictureImage(property.getCoverPhoto(), baseViewHolder.bkg, null);
        baseViewHolder.price.setText(property.getPrice().getFormatted());
        //todo: locale title\address parser
        baseViewHolder.title.setText(property.getAddress().getStreetName() + property.getHouseNumber() + " Street");
        baseViewHolder.address.setText(property.getAddress().getCityName());
        baseViewHolder.roomsCounter.setText(property.getRoomsCount() + "");
        baseViewHolder.bathCounter.setText(property.getBathroomCount() + "");
        baseViewHolder.size.setText(property.getFloorArea() + "");
        baseViewHolder.sqrm.setText(property.getPlotArea() + "");

        if(property.isFavorite()){
            baseViewHolder.favBtn.setSelected(true);
        }else{
            baseViewHolder.favBtn.setSelected(false);
        }

        baseViewHolder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    favProp.remove(property);
                    new ApiUnPinProperty(context).request(property.getId(), null, new Action() {
                        @Override
                        public void execute() {
                            v.setSelected(true);
                            favProp.add(property);
                        }
                    });
                } else {
                    v.setSelected(true);
                    favProp.add(property);
                    new ApiPinProperty(context).request(property.getId(), null, new Action() {
                        @Override
                        public void execute() {
                            v.setSelected(false);
                            favProp.remove(property);
                        }
                    });
                }
            }
        });

        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyPageActivity.startWithProperty(context, property);
            }
        });

        return convertView;
    }

}

