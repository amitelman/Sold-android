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

import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;

public class OpenHouseDaysAdapter extends RecyclerView.Adapter<OpenHouseDaysAdapter.DayViewHolder> {

    private List<OpenHouse> openHouses;
    private TAction<OpenHouse> onDayPress;
    OpenHouse selected;

    public OpenHouseDaysAdapter(List<OpenHouse> openHouses, TAction<OpenHouse> onDayPress) {
        this.openHouses = openHouses;
        this.onDayPress = onDayPress;
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

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_house_day_item, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DayViewHolder holder, int position) {
        final OpenHouse openHouse = openHouses.get(position);

        holder.day.setText(openHouse.getWday() + "");
        holder.number.setText(openHouse.getDay() + "");
        holder.month.setText(openHouse.getMonth() + "");

        if(selected != null && selected == openHouse){
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

        holder.bkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected != openHouse){
                    selected = openHouse;
                    if(onDayPress != null)
                        onDayPress.execute(openHouse);
                    notifyDataSetChanged();
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



}