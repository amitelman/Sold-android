package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;

/**
 * Created by monkey on 25/06/2015.
 */
public class PropertyAdditionalFeaturesAdapter extends BaseAdapter {
    private Context context;
    private List<IdLabel> properties;
    private LayoutInflater inflater;

    private List<IdLabel> selectedProperties;

    public PropertyAdditionalFeaturesAdapter(Context context, List<IdLabel> properties) {
        this.context = context;
        if (properties != null)
            this.properties = properties;
        selectedProperties = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void restoreLast(String featuresCsv) {
        if(featuresCsv.length() > 0){
            List<String> items = Arrays.asList(featuresCsv.split("\\s*,\\s*"));
            for(IdLabel prop : properties){
                for(String s: items){
                    if(prop.getId() == Long.parseLong(s))
                        selectedProperties.add(prop);
                }
            }
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        if (properties != null)
            return properties.size();
        return 0;
    }

    public String getFeaturesIdCsv(){
        String selectedCsv = "";
        for(IdLabel item: selectedProperties){
            selectedCsv += item.getId() + ",";
        }
        return selectedCsv;
    }

    public void clearSelected(){
        selectedProperties.clear();
        notifyDataSetChanged();
    }


    @Override
    public IdLabel getItem(int i) {
        return properties.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class BaseViewHolder {
        FrameLayout bkg;
        TextView item;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.additional_feature_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.additionalFeatureBkg);
            baseViewHolder.item = convertView.findViewById(R.id.additionalFeatureItem);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final IdLabel item = getItem(position);
        baseViewHolder.item.setText(item.getLabel());

        if (selectedProperties.contains(item)) {
            baseViewHolder.item.setSelected(true);
        } else {
            baseViewHolder.item.setSelected(false);
        }


        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedProperties.contains(item)) {
                    selectedProperties.remove(item);
                } else {
                    selectedProperties.add(item);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


}


