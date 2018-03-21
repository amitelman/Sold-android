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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.pagination.abs.PagibaleAdapter;

/**
 * Created by monkey on 25/06/2015.
 */
public class PropertyTypeAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<PropertyType> properties;
    private LayoutInflater inflater;

    public PropertyTypeAdapter(Context context, List<PropertyType> properties) {
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
    public PropertyType getItem(int i) {
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

        final BaseViewHolder finalBaseViewHolder1 = baseViewHolder;
        ImageLoader.getInstance().loadImage(item.getIcon(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    ((ImageView)finalBaseViewHolder1.icon).setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });

        final BaseViewHolder finalBaseViewHolder = baseViewHolder;
        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = item.isSelected();
                if (isSelected){//set selected ui
                    finalBaseViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.lipstick));
                }else{
                    finalBaseViewHolder.icon.setColorFilter(context.getResources().getColor(R.color.white));
                }
                    finalBaseViewHolder.item.setSelected(!isSelected);
                    finalBaseViewHolder.bkg.setSelected(!isSelected);
                    item.setSelected(!isSelected);
//                notifyDataSetChanged();


            }
        });

        return convertView;
    }


}


