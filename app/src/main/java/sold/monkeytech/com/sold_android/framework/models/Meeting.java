package sold.monkeytech.com.sold_android.framework.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sold.monkeytech.com.sold_android.framework.cache.MeetingCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 22/04/2018.
 */

public class Meeting extends BaseModel<Meeting> {

    private String buyerName;
    private String agentName;
    private String startAt;
    private String endsAt;
    private String address;

    public DateTime getStartDateTimeFromString(){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        DateTime dt = formatter.parseDateTime(getStartAt());
        return dt;
    }

    public DateTime getEndDateTimeFromString(){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        DateTime dt = formatter.parseDateTime(getEndsAt());
        return dt;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return MeetingCache.getInstance();
    }

    @Override
    protected Class getType() {
        return Meeting.class;
    }

    public String getTime() {
        DateTime startDate = getStartDateTimeFromString();
        DateTime endDate = getEndDateTimeFromString();
        if(startDate != null && endDate != null)
            return startDate.getHourOfDay() + ":" + (startDate.getMinuteOfHour() == 0 ? "00" : startDate.getMinuteOfHour() + "0" ) + " - " +
                    endDate.getHourOfDay() + (endDate.getMinuteOfHour() == 0 ? "00" : endDate.getMinuteOfHour() + "0" );
        return "";
    }
}
