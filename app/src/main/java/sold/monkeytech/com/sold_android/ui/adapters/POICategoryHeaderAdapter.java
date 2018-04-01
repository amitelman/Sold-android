package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.models.Category;

public class POICategoryHeaderAdapter extends RecyclerView.Adapter<POICategoryHeaderAdapter.ViewHolder> {

    private TAction<Category> onCategoryAction;
    List<Category> categories;
    Context mContext;

    private Category selectedItem;

    public POICategoryHeaderAdapter(Context mContext, List<Category> categories, TAction<Category> onCategoryAction) {
        this.categories = categories;
        this.mContext = mContext;
        this.onCategoryAction = onCategoryAction;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bkg;
        TextView title;
        ImageView arrow;

        ViewHolder(View v) {
            super(v);
            bkg = v.findViewById(R.id.poiCategoryItemBkg);
            title = v.findViewById(R.id.poiCategoryItemTitle);
            arrow = v.findViewById(R.id.poiCategoryItemArrow);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_category_header_item, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Category category = categories.get(position);

        holder.title.setText(category.getName());

        if(selectedItem == category){
            holder.arrow.setVisibility(View.VISIBLE);
            holder.arrow.setColorFilter(Color.parseColor(category.getColor()));
            holder.bkg.setBackgroundColor(Color.parseColor(category.getColor()));
            holder.title.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            holder.arrow.setVisibility(View.INVISIBLE);
            holder.bkg.setBackgroundColor(Color.parseColor("#cfd8e3"));
            holder.title.setTextColor(Color.parseColor("#3a3f46"));
//            holder.title.setSelected(false);
        }

        holder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = category;
                holder.title.setSelected(!holder.title.isSelected());
                notifyDataSetChanged();
                if(onCategoryAction != null)
                    onCategoryAction.execute(category);
//                v.setSelected(!v.isSelected());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



}