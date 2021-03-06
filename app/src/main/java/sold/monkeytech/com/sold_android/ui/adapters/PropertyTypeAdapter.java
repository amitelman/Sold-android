package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.TAction;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.pagination.abs.PagibaleAdapter;

/**
 * Created by monkey on 25/06/2015.
 */
public class PropertyTypeAdapter extends BaseAdapter {
    private Context context;
    private List<PropertyType> properties;
    private LayoutInflater inflater;

    private List<PropertyType> selectedTypes;

    public PropertyTypeAdapter(Context context, List<PropertyType> properties) {
        this.context = context;
        if (properties != null)
            this.properties = properties;
        selectedTypes = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (properties != null)
            return properties.size();
        return 0;
    }

    public String getSelectedTypesIdCsv(){
        String selectedCsv = "";
        for(PropertyType pt : selectedTypes){
            selectedCsv += pt.getId() + ",";
        }
        return selectedCsv;
    }

    public void clearSelected(){
        selectedTypes.clear();
        notifyDataSetChanged();
    }

    @Override
    public PropertyType getItem(int i) {
        return properties.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void restoreLast(String typesCsv) {
        if(typesCsv.length() > 0){
            List<String> items = Arrays.asList(typesCsv.split("\\s*,\\s*"));
                for(PropertyType pt : properties){
                    for(String s: items){
                        if(pt.getId() == Long.parseLong(s))
                            selectedTypes.add(pt);
                    }
                }
                notifyDataSetChanged();
        }
    }


    public static class BaseViewHolder {
        LinearLayout bkg;
        AppCompatImageView icon;
        TextView item;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.property_type_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.propertyItemBkg);
            baseViewHolder.icon = convertView.findViewById(R.id.propertyItemImg);
            baseViewHolder.item = convertView.findViewById(R.id.propertyItem);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final PropertyType item = getItem(position);
        baseViewHolder.item.setText(item.getName());

        final BaseViewHolder finalBaseViewHolder = baseViewHolder;
        ImageLoaderUtils.loadHighResPicture(item.getIcon(), new TAction<Bitmap>() {
            @Override
            public void execute(Bitmap bitmap) {
                ((ImageView)finalBaseViewHolder.icon).setImageBitmap(bitmap);
            }
        });


        if(selectedTypes.contains(item)) {
            finalBaseViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.white));
            finalBaseViewHolder.item.setSelected(true);
            finalBaseViewHolder.bkg.setSelected(true);
        }else{
            finalBaseViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.lipstick));
            finalBaseViewHolder.item.setSelected(false);
            finalBaseViewHolder.bkg.setSelected(false);
        }

        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTypes.contains(item)){
                    selectedTypes.remove(item);
                }else{
                    selectedTypes.add(item);
                }
                notifyDataSetChanged();
            }
        });


        return convertView;
    }


}


