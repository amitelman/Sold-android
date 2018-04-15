package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;
import sold.monkeytech.com.sold_android.framework.managers.DateUtils;
import sold.monkeytech.com.sold_android.framework.models.PropertyType;
import sold.monkeytech.com.sold_android.framework.models.SavedSearch;

/**
 * Created by monkey on 25/06/2015.
 */
public class SavedSearchAdapter extends BaseAdapter {
    public static final int REGULAR = 0;
    public static final int EDIT = 1;

    private List<SavedSearch> savedSearches;
    private LayoutInflater inflater;

    private int adapterType = REGULAR;
    private List<SavedSearch> selectedItems;
    private final TAction<SavedSearch> onItemSelect;

    public SavedSearchAdapter(Context context, List<SavedSearch> savedSearches, TAction<SavedSearch> onItemSelect) {
//        this.context = context;
        if (savedSearches != null)
            this.savedSearches = savedSearches;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        selectedItems = new ArrayList<>();
        this.onItemSelect = onItemSelect;
    }

    @Override
    public int getCount() {
        if (savedSearches != null)
            return savedSearches.size();
        return 0;
    }

    @Override
    public SavedSearch getItem(int i) {
        return savedSearches.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void selectAll() {
        selectedItems.clear();
        selectedItems.addAll(savedSearches);
        notifyDataSetChanged();
    }

    public void deselectAll() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public List<SavedSearch> getSelectedItems(){
        return selectedItems;
    }

    public void deleteSelected(List<SavedSearch> savedSearches) {
        this.savedSearches.removeAll(savedSearches);
        this.selectedItems.removeAll(savedSearches);
        notifyDataSetChanged();
    }

    public void addToSelected(List<SavedSearch> savedSearches){
        this.savedSearches.addAll(savedSearches);
        this.selectedItems.addAll(savedSearches);
        notifyDataSetChanged();
    }


    public static class BaseViewHolder {
        LinearLayout bkg;
        TextView date;
        TextView title;
        TextView counter;
        CheckBox cb;
        View sep;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.saved_searches_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.savedSearchesItemLayout);
            baseViewHolder.date = convertView.findViewById(R.id.savedSearchesItemDate);
            baseViewHolder.title = convertView.findViewById(R.id.savedSearchesItemTitle);
            baseViewHolder.counter = convertView.findViewById(R.id.savedSearchesItemFilterCount);
            baseViewHolder.cb = convertView.findViewById(R.id.savedSearchesItemCb);
            baseViewHolder.sep = convertView.findViewById(R.id.savedSearchItemSep);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final SavedSearch item = getItem(position);
        baseViewHolder.date.setText(DateUtils.getParsedDate(item.getCreatedAt()));
        baseViewHolder.title.setText(item.getName());
        baseViewHolder.counter.setText(item.getFilterCount());

        baseViewHolder.cb.setOnCheckedChangeListener(null);
        if(selectedItems.contains(item)){
            baseViewHolder.cb.setChecked(true);
        }else{
            baseViewHolder.cb.setChecked(false);
        }

        if(adapterType == REGULAR){
            baseViewHolder.cb.setVisibility(View.GONE);
        }else{
            baseViewHolder.cb.setVisibility(View.VISIBLE);
            baseViewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedItems.add(item);
                        Log.d("wowSaved","added");
                    }else{
                        selectedItems.remove(item);
                        Log.d("wowSaved","removed");
                    }
                }
            });
        }

        if(position == getCount())
            baseViewHolder.sep.setVisibility(View.GONE);


        if(adapterType == REGULAR){
            baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemSelect != null)
                        onItemSelect.execute(item);
                }
            });
        }else{
            baseViewHolder.bkg.setOnClickListener(null);
        }

        return convertView;
    }

    public void setAdapterType(int type){
        this.adapterType = type;
        notifyDataSetChanged();
    }


}


