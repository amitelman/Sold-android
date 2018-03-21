package sold.monkeytech.com.sold_android.ui.adapters.expandable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.POI;


public class SecondLevelAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<List<Meta>> data;
    List<POI> headers;

    public SecondLevelAdapter(Context context, List<POI> headers, List<List<Meta>> data) {
        this.context = context;
        this.headers = headers;
        this.data = data;
    }

    @Override
    public POI getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_second, null);

        TextView text = (TextView) convertView.findViewById(R.id.expListSecondRowText);
        RelativeLayout layout = convertView.findViewById(R.id.expListSecondRowBkg);

        final POI curPoi = getGroup(groupPosition);

        text.setText(curPoi.getName());

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("wowPOI", curPoi.getLat());
//            }
//        });

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Meta> childData = data.get(groupPosition);
        return childData.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_third, null);

        TextView textView = (TextView) convertView.findViewById(R.id.rowThirdText);

        List<Meta> childArray = data.get(groupPosition);

        String text = childArray.get(childPosition).getKey() + " / " + childArray.get(childPosition).getValue();

        textView.setText(text);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        List<Meta> children = data.get(groupPosition);
        return data.get(groupPosition).size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}