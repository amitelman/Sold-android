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

public class ServicePagesAdapter extends RecyclerView.Adapter<ServicePagesAdapter.ViewHolder> {

    List<ServicePage> servicePages;
    Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        ImageView image;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.myServiceItemTitle);
            content = v.findViewById(R.id.myServiceItemContent);
            image = v.findViewById(R.id.myServiceItemImg);
        }
    }

    public ServicePagesAdapter(Context mContext, List<ServicePage> servicePages) {
        this.servicePages = servicePages;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_service_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ServicePage page = servicePages.get(position);

        holder.title.setText(page.getTitle());
        holder.content.setText(page.getContent());
        ImageLoaderUtils.loadBigPictureImage(page.getImage(), holder.image, null);
    }

    @Override
    public int getItemCount() {
        return servicePages.size();
    }



}