package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.ServicePage;

public class PropertyFeaturesImagesAdapter extends RecyclerView.Adapter<PropertyFeaturesImagesAdapter.ViewHolder> {

    List<String> images;
    Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.img);
        }
    }

    public PropertyFeaturesImagesAdapter(Context mContext, List<String> images) {
        this.images = images;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_features_image_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String image = images.get(position);

        ImageLoaderUtils.loadBigPictureImage(null, holder.image, null);
        ImageLoaderUtils.loadBigPictureImage(image, holder.image, null);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }



}