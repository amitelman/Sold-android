package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.Address;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.Location;

/**
 * Created by monkey on 25/06/2015.
 */
public class SearchLocationAdapter extends BaseAdapter implements View.OnClickListener {

    private static final int CITY = 0;
    private static final int STREET = 1;
    private static final int BOTH = 2;

    private final int type;
    private TAction<Location> onLocationClick;
    private Context context;
    private List<Location> locations;
    private LayoutInflater inflater;
    private IdLabel selectedItem;

    public SearchLocationAdapter(Context context, int type, List<Location> locations, TAction<Location> onLocationClick) {
        this.context = context;
        this.type = type;
        if (locations != null)
            this.locations = locations;
        this.onLocationClick = onLocationClick;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (locations != null)
            return locations.size();
        return 0;
    }

    @Override
    public Location getItem(int i) {
        return locations.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    public void refreshList(List<Location> locations) {
        if(this.locations == null)
            this.locations = new ArrayList<>();
        this.locations.clear();
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }


    public static class BaseViewHolder {
        RelativeLayout bkg;
        TextView title;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_string_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.simpleItemLayout);
            baseViewHolder.title = convertView.findViewById(R.id.simpleItemTitle);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final Location item = getItem(position);

//        baseViewHolder.title.setText(type == CITY ? item.getCityName() : item.getStreetName());
        baseViewHolder.title.setText(item.getLocationName());
        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onLocationClick != null)
                    onLocationClick.execute(item);
            }
        });

        return convertView;
    }


}



