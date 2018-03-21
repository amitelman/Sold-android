package sold.monkeytech.com.sold_android.ui.adapters.expandable;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.Category;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.POI;

public class ThreeLevelListAdapter extends BaseExpandableListAdapter {

    private TAction<POI> onPoiClick;
    List<Category> parentCategories;
    List<List<POI>> secondLevelPoi;
    private Context context;
    List<HashMap<POI, List<Meta>>> data;

    public ThreeLevelListAdapter(Context context, List<Category> parentCategories, List<List<POI>> secondLevelPoi, List<HashMap<POI, List<Meta>>> data, TAction<POI> onPoiClick) {
        this.context = context;
        this.parentCategories = parentCategories;
        this.secondLevelPoi = secondLevelPoi;
        this.data = data;
        if(onPoiClick != null)
            this.onPoiClick = onPoiClick;
    }

    @Override
    public int getGroupCount() {
        return parentCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {
        return child;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_first, null);

        TextView text = convertView.findViewById(R.id.expListFirstRowText);
        FrameLayout bkg = convertView.findViewById(R.id.expListFirstRowBkg);

        text.setText(parentCategories.get(groupPosition).getName());
        bkg.setBackgroundColor(Color.parseColor(parentCategories.get(groupPosition).getColor()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

        List<POI> PoiHeaders = secondLevelPoi.get(groupPosition);

        List<List<Meta>> childData = new ArrayList<>();
        //each poi one child
        for(HashMap<POI, List<Meta>> mapItem : data){
            for(POI key : mapItem.keySet()){
                childData.add(mapItem.get(key));
            }
        }
        secondLevelELV.setAdapter(new SecondLevelAdapter(context, PoiHeaders, childData));
        secondLevelELV.setGroupIndicator(null);
        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    secondLevelELV.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        secondLevelELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Log.d("wowPOI", "poi3");
                return true;
            }
        });

        secondLevelELV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long position) {
                Log.d("wowPOI", "poi2");
                POI poi = secondLevelPoi.get(groupPosition).get((int) position);
                if(onPoiClick != null)
                    onPoiClick.execute(poi);
                return false;
            }
        });


        return secondLevelELV;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
