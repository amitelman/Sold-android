package sold.monkeytech.com.sold_android.framework.models;


import java.util.List;

import sold.monkeytech.com.sold_android.framework.cache.OpenHouseCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class OpenHouse extends BaseModel<OpenHouse> {

    private String wday;
    private int day;
    private String month;
    private List<String> slots;

    public String getWday() {
        return wday;
    }

    public void setWday(String wday) {
        this.wday = wday;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return OpenHouseCache.getInstance();
    }

    @Override
    protected Class getType() {
        return OpenHouse.class;
    }
}
