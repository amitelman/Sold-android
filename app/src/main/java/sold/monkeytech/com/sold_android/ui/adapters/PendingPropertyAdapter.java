package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.Property;

/**
 * Created by monkey on 25/06/2015.
 */
public class PendingPropertyAdapter extends BaseAdapter {
    private Context context;
    private List<Property> properties;
    private LayoutInflater inflater;


    public PendingPropertyAdapter(Context context, List<Property> properties) {
        this.context = context;
        if (properties != null)
            this.properties = properties;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (properties != null)
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


    public static class BaseViewHolder {
        FrameLayout bkg;
        TextView title;
        TextView price;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pending_property_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.pendingPropertyItemBkg);
            baseViewHolder.title = convertView.findViewById(R.id.pendingPropertyItemTitle);
            baseViewHolder.price = convertView.findViewById(R.id.pendingPropertyItemPrice);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final Property item = getItem(position);
        baseViewHolder.title.setText(item.getFullAddress());
        if(item.getPrice() != null)
            baseViewHolder.price.setText(item.getPrice().getFormatted());

        return convertView;
    }


}


