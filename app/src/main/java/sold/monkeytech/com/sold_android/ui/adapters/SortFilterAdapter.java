package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.MyAnimationUtils;
import sold.monkeytech.com.sold_android.framework.managers.SearchParamManager;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;

/**
 * Created by monkey on 25/06/2015.
 */
public class SortFilterAdapter extends BaseAdapter implements View.OnClickListener {

    private final int ASC = 0;
    private final int DESC = 1;

    private Context context;
    private List<IdLabel> sortables;
    private LayoutInflater inflater;
    private IdLabel selectedItem;
    private int sortDirection = ASC;

    public SortFilterAdapter(Context context, List<IdLabel> sortables) {
        this.context = context;
        if (sortables != null)
            this.sortables = sortables;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(SearchParamManager.getInstance().getSortId() != 0){
            for(IdLabel sort : sortables){
                if(sort.getId() == SearchParamManager.getInstance().getSortId()){
                    selectedItem = sort;
                    if(SearchParamManager.getInstance().getSortDirection().equals("desc"))
                        sortDirection = DESC;
                }
            }
        }
    }

    @Override
    public int getCount() {
        if (sortables != null)
            return sortables.size();
        return 0;
    }

    @Override
    public IdLabel getItem(int i) {
        return sortables.get(i);
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
        ImageButton v;
        TextView title;
        LinearLayout directionLayout;
        TextView direction;
        ImageButton arrow;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.sort_filter_item, parent, false);
            baseViewHolder.bkg = convertView.findViewById(R.id.sortActItemLayout);
            baseViewHolder.v = convertView.findViewById(R.id.sortActItemBtn);
            baseViewHolder.title = convertView.findViewById(R.id.sortActItemTitle);
            baseViewHolder.directionLayout = (LinearLayout) convertView.findViewById(R.id.sortActItemDirectionLayout);
            baseViewHolder.direction = convertView.findViewById(R.id.sortActItemDirection);
            baseViewHolder.arrow = convertView.findViewById(R.id.sortActItemArrow);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final IdLabel item = getItem(position);
        baseViewHolder.title.setText(item.getLabel());

        if(selectedItem != null && item.getId() == selectedItem.getId()){
            baseViewHolder.directionLayout.setVisibility(View.VISIBLE);
            baseViewHolder.v.setVisibility(View.VISIBLE);

            if(sortDirection == DESC){
                baseViewHolder.direction.setText("DESC");
                MyAnimationUtils.rotateRight(baseViewHolder.arrow);
            }

            final BaseViewHolder finalBaseViewHolder = baseViewHolder;
            baseViewHolder.directionLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sortDirection == ASC){
                        finalBaseViewHolder.direction.setText("DESC");
                        MyAnimationUtils.rotateRight(finalBaseViewHolder.arrow);
                        sortDirection = DESC;
                    }else{
                        finalBaseViewHolder.direction.setText("ASC");
                        MyAnimationUtils.rotateLeft(finalBaseViewHolder.arrow);
                        sortDirection = ASC;
                    }
                }
            });
        }else{
            baseViewHolder.directionLayout.setVisibility(View.GONE);
            baseViewHolder.v.setVisibility(View.INVISIBLE);
        }

        baseViewHolder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = item;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public long getSotrableId(){
        if(selectedItem != null)
            return selectedItem.getId();
        return -1;
    }

    public String getSortDirection(){
        if (sortDirection == ASC)
            return "asc";
        else return "desc";
    }


}



