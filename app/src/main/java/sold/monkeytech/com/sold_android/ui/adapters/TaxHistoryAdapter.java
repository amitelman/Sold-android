package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.TaxRecord;

public class TaxHistoryAdapter extends BaseExpandableListAdapter {
 
    private Context context;
//    private HashMap<Integer, String> parentData; // header titles
    // child data in format of header title, child title
//    private HashMap<HashMap<Integer, String>, String> listDataChild;
    private List<TaxRecord> records;
 
    public TaxHistoryAdapter(Context context, List<TaxRecord> records) {
        this.context = context;
        this.records = records;
//        this.parentData = taxRecords;
//        this.listDataChild = listChildData;
    }
 
    @Override
    public TaxRecord getChild(int groupPosition, int childPosititon) {
//        return this.listDataChild.get(childPosititon);
        return records.get(groupPosition);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
 
//        final String childText = (String) getChild(groupPosition, childPosition);
        final TaxRecord record = getGroup(groupPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.tax_history_child_item, null);
        }
            TextView description = convertView.findViewById(R.id.taxHistoryChildItemDescription);
        description.setText(record.getDescription());
//        year.setText(childText);
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }
 
    @Override
    public TaxRecord getGroup(int groupPosition) {
        return records.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return records.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {

//        String headerTitle = (String) getGroup(groupPosition);
        final TaxRecord record = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.tax_history_item, null);
        }

        TextView year = (TextView) convertView.findViewById(R.id.taxHistoryItemYear);
        TextView tax = (TextView) convertView.findViewById(R.id.taxHistoryItemTax);
        ImageView arrow = convertView.findViewById(R.id.taxHistoryItemArrow);

        year.setText(record.getYear() + "");
        tax.setText(record.getAmount().getFormatted());
//        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
//        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}