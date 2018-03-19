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

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;

/**
 * Created by monkey on 25/06/2015.
 */
public class PropertyFeaturesAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<IdLabel> properties;
    private LayoutInflater inflater;

    public PropertyFeaturesAdapter(Context context, List<IdLabel> properties) {
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
    public IdLabel getItem(int i) {
        return properties.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

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


        final BaseViewHolder finalBaseViewHolder = baseViewHolder;
        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(finalBaseViewHolder.item.isSelected()){
                   finalBaseViewHolder.item.setSelected(false);
               }else{
                   finalBaseViewHolder.item.setSelected(true);
               }

            }
        });

        return convertView;
    }


}


