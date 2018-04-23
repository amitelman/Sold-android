package sold.monkeytech.com.sold_android.framework.parsers;


import org.json.JSONException;
import org.json.JSONObject;

import sold.monkeytech.com.sold_android.framework.models.Meeting;
import sold.monkeytech.com.sold_android.framework.models.Meta;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.parsers.abs.GeneralParser;

/**
 * Created by MonkeyFather on 28/08/2017.
 */

public class MeetingParser extends GeneralParser<Meeting> {

    public MeetingParser() {
        super();
    }

    @Override
    public Meeting parseToSingle(JSONObject jo) throws JSONException, InstantiationException, IllegalAccessError {

        Meeting meeting = null;
        meeting = getObjectFromCache(jo,"id");
        if(meeting == null){
            meeting = new Meeting();
            meeting.setId(safeParseLong(jo, "id"));
        }


        meeting.setBuyerName(safeParseString(jo, "buyer_name"));
        meeting.setAgentName(safeParseString(jo, "agent_name"));
        meeting.setStartAt(safeParseString(jo, "starts_at"));
        meeting.setEndsAt(safeParseString(jo, "ends_at"));
        meeting.setAddress(safeParseString(jo, "address"));

        return meeting;
    }

    @Override
    protected Class getType() {
        return Meeting.class;
    }
}

