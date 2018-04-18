package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;

public class OpenHouseHoursAdapter extends RecyclerView.Adapter<OpenHouseHoursAdapter.HourViewHolder> {

    private static final int REGULAR_MODE = 0;
    private static final int EDIT_MODE = 1;
    private int type = REGULAR_MODE;
    private List<OpenHouseSlots> openHousesSlots;
    private TAction<OpenHouseSlots> onHourClick;
    private OpenHouseSlots selected;
    private List<OpenHouseSlots> deleted;

    public class HourViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bkg;
        TextView hour;

        HourViewHolder(View v) {
            super(v);
            bkg = v.findViewById(R.id.hourItemBkg);
            hour = v.findViewById(R.id.hourItem);
        }
    }

    public OpenHouseHoursAdapter(List<OpenHouseSlots> openHousesSlots, TAction<OpenHouseSlots> onHourClick, boolean canDelete) {
        if(openHousesSlots != null)
            this.openHousesSlots = openHousesSlots;
        if(onHourClick != null)
            this.onHourClick = onHourClick;
        if(canDelete){
            this.type = EDIT_MODE;
            deleted = new ArrayList<>();
        }
    }

    public void updateItems(List<OpenHouseSlots> newSlots){
        if(openHousesSlots == null)
            openHousesSlots = new ArrayList<>();
        openHousesSlots.clear();
        openHousesSlots.addAll(newSlots);
        notifyDataSetChanged();
    }


    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(type == REGULAR_MODE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_house_hour_item, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_house_hour_item_edit, parent, false);
        }
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        final OpenHouseSlots slot = openHousesSlots.get(position);

        holder.hour.setText(slot.getName());


        holder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = slot;
                if(onHourClick != null)
                    onHourClick.execute(slot);
                notifyDataSetChanged();
            }
        });

        if(type == REGULAR_MODE){
            if(selected != null &&selected == slot){
                holder.bkg.setSelected(true);
                holder.hour.setSelected(true);
            }else{
                holder.bkg.setSelected(false);
                holder.hour.setSelected(false);
            }
        }else{
            if(deleted.contains(slot)){
                holder.bkg.setSelected(true);
                holder.hour.setSelected(true);
            }else{
                holder.bkg.setSelected(false);
                holder.hour.setSelected(false);
            }
        }

        holder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == REGULAR_MODE){
                    selected = slot;
                    if(onHourClick != null)
                        onHourClick.execute(slot);
                    notifyDataSetChanged();
                }else{
                    if(deleted.contains(slot)){
                        deleted.remove(slot);
                    }else{
                        deleted.add(slot);
                    }
                    notifyDataSetChanged();;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(openHousesSlots == null)
            return 0;
        return openHousesSlots.size();
    }

    public String getDeleted(){
        String csv = "";
        for(OpenHouseSlots ohs : deleted)
            csv += ohs.getId() + ",";
        return csv;
    }

    public void deleteDeleted() {
        openHousesSlots.removeAll(deleted);
        notifyDataSetChanged();
//        for(OpenHouse oh : deleted){
//            if(openHouses.contains(oh))
//        }
    }

    public void clearDeleted(){
        deleted.clear();
    }

    public void restoreDeleted(){
        openHousesSlots.addAll(deleted);
    }

}