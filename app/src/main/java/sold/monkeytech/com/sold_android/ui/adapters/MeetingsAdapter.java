package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.LocManager;
import sold.monkeytech.com.sold_android.framework.models.Meeting;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Property;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiDeleteMeeting;

/**
 * Created by monkey on 25/06/2015.
 */
public class MeetingsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<Meeting> meetings;
    private long propertyId;

    public MeetingsAdapter(Context context, List<Meeting> meetings, long propertyId) {
        this.context = context;
        if (meetings != null)
            this.meetings = meetings;
        this.propertyId = propertyId;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (meetings != null)
            return meetings.size();
        else return 0;
    }

    @Override
    public Meeting getItem(int i) {
        if(i < 0)
            return null;
        return meetings.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class BaseViewHolder {
        TextView time;
        TextView buyer;
        TextView agent;
        TextView address;
        TextView cancelBtn;
    }

    public void updateItems(List<Meeting> newMeetings) {
        if (meetings == null)
            meetings = new ArrayList<>();
        meetings.clear();
        meetings.addAll(newMeetings);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = new BaseViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.meetings_item, parent, false);
            baseViewHolder.time = convertView.findViewById(R.id.meetingItemTime);
            baseViewHolder.buyer = convertView.findViewById(R.id.meetingItemBuyer);
            baseViewHolder.agent = convertView.findViewById(R.id.meetingItemAgent);
            baseViewHolder.address = convertView.findViewById(R.id.meetingItemAddress);
            baseViewHolder.cancelBtn = convertView.findViewById(R.id.meetingItemCancel);

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
        }

        final Meeting meeting = getItem(position);

        baseViewHolder.time.setText(meeting.getTime());
        baseViewHolder.buyer.setText(meeting.getBuyerName());
        baseViewHolder.agent.setText(meeting.getAgentName());
        baseViewHolder.address.setText(meeting.getAddress());
        baseViewHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler handler = new Handler();
                meetings.remove(meeting);
                notifyDataSetChanged();
                new ApiDeleteMeeting(context).request((int) propertyId, meeting.getId(), null, new Action() {
                    @Override
                    public void execute() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                meetings.add(meeting);
                                notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        });
        return convertView;
    }


}


