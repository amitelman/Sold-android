package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.Property;

/**
 * Created by monkey on 25/06/2015.
 */
public class SearchInListAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context context;
    private List<Property> properties;
    private LayoutInflater inflater;

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
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.sold_search_item_header, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.searchItemHeaderTitle);
            holder.address = (TextView) convertView.findViewById(R.id.searchItemHeaderAddress);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
//        String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
//        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return 0;//properties[position];
    }

    public static class HeaderViewHolder {
        TextView title;
        TextView address;
    }

    public static class BaseViewHolder{
        RelativeLayout bkg;
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

//        baseViewHolder.companyName.setText(member.getCompany());
//        baseViewHolder.name.setText(member.getFirstName() + " " + member.getLastName());
//        baseViewHolder.email.setText(member.getEmail());
//        baseViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(memberTAction != null)
//                    memberTAction.execute(member);
//            }
//        });

        return convertView;
    }

}
