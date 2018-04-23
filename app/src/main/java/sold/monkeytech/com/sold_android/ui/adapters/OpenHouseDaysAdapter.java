package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.TAction;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;

public class OpenHouseDaysAdapter extends RecyclerView.Adapter<OpenHouseDaysAdapter.DayViewHolder> {

    private static final int REGULAR_MODE = 0;
    private static final int EDIT_MODE = 1;
    private List<OpenHouse> openHouses;
    private TAction<OpenHouse> onDayPress;
    OpenHouse selected = new OpenHouse();
    private long selectedId = 0;
    private int type = REGULAR_MODE;

    private List<OpenHouse> deleted;
    private OpenHouse lastClicked = new OpenHouse();

    public OpenHouseDaysAdapter(List<OpenHouse> openHouses, TAction<OpenHouse> onDayPress, boolean canDelete) {
        if(openHouses != null)
            this.openHouses = openHouses;
        this.onDayPress = onDayPress;
        if(canDelete){
            type = EDIT_MODE;
            deleted = new ArrayList<>();
        }
    }



    public class DayViewHolder extends RecyclerView.ViewHolder {
        LinearLayout bkg;
        TextView day;
        TextView number;
        TextView month;
        ImageView arrow;

        DayViewHolder(View v) {
            super(v);
            bkg = v.findViewById(R.id.dayItemBkg);
            day = v.findViewById(R.id.dayItemDay);
            number = v.findViewById(R.id.dayItemNumber);
            month = v.findViewById(R.id.dayItemMounth);
            arrow = v.findViewById(R.id.dayItemArrow);
        }
    }

    public void addItems(List<OpenHouse> openHouses){
        if(this.openHouses == null)
            this.openHouses = new ArrayList<>();
        this.openHouses.clear();
        this.openHouses.addAll(openHouses);
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(type == REGULAR_MODE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_house_day_item, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_house_day_item_edit, parent, false);
        }
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DayViewHolder holder, int position) {
        final OpenHouse openHouse = openHouses.get(position);

        holder.day.setText(openHouse.getWday() + "");
        holder.number.setText(openHouse.getDay() + "");
        holder.month.setText(openHouse.getMonth() + "");

        if(type == REGULAR_MODE){
            if(selectedId != 0 && selectedId == openHouse.getId()){
                holder.number.setSelected(true);
                holder.month.setSelected(true);
                holder.day.setSelected(true);
                holder.bkg.setSelected(true);
                holder.arrow.setVisibility(View.VISIBLE);
            }else{
                holder.number.setSelected(false);
                holder.month.setSelected(false);
                holder.day.setSelected(false);
                holder.bkg.setSelected(false);
                holder.arrow.setVisibility(View.INVISIBLE);
            }
        }
        if(type == EDIT_MODE){
            if(deleted.contains(openHouse)){
                holder.number.setSelected(true);
                holder.month.setSelected(true);
                holder.day.setSelected(true);
                holder.bkg.setSelected(true);
            }else{
                holder.number.setSelected(false);
                holder.month.setSelected(false);
                holder.day.setSelected(false);
                holder.bkg.setSelected(false);
            }
            if(selectedId != 0 && selectedId == openHouse.getId()){
                holder.arrow.setVisibility(View.VISIBLE);
            }else{
                holder.arrow.setVisibility(View.INVISIBLE);
            }
        }

        holder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == REGULAR_MODE){
                    if(selectedId != openHouse.getId()){
                        selectedId = openHouse.getId();
                        notifyDataSetChanged();
                        if(onDayPress != null)
                            onDayPress.execute(openHouse);
                    }
                }else{
                    if(selectedId != openHouse.getId()) {
                        selectedId = openHouse.getId();
                    }
                    if(openHouse == lastClicked){
                        if(deleted.contains(openHouse)){
                            deleted.remove(openHouse);
                        }else{
                            deleted.add(openHouse);
                            if(onDayPress != null)
                                onDayPress.execute(openHouse);
                        }
                    }else{
                        lastClicked = openHouse;
                        if(deleted.contains(openHouse)){
                            if(onDayPress != null)
                                onDayPress.execute(openHouse);
                        }else{
                            if(deleted.contains(openHouse)){
                                deleted.remove(openHouse);
                            }else{
                                deleted.add(openHouse);
                                if(onDayPress != null)
                                    onDayPress.execute(openHouse);
                            }
                        }
                    }

                    notifyDataSetChanged();;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(openHouses == null)
            return 0;
        return openHouses.size();
    }

    public String getDeleted(){
        String csv = "";
        for(OpenHouse oh : deleted)
            csv += oh.getId() + ",";
        return csv;
    }

    public void deleteDeleted() {
        openHouses.removeAll(deleted);
        notifyDataSetChanged();
//        for(OpenHouse oh : deleted){
//            if(openHouses.contains(oh))
//        }
    }

    public List<OpenHouse> getDeletedObj(){
        if(deleted != null)
            return deleted;
        else
            return new ArrayList<>();
    }

    public void clearDeleted(){
        deleted.clear();
    }

    public void restoreDeleted(){
        openHouses.addAll(deleted);
    }

}