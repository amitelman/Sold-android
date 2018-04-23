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
    private List<OpenHouseSlots> slots;

    public OpenHouse(){
    }

    public OpenHouse(String wDay, int day, String month, List<OpenHouseSlots> slots){
        setWday(wDay);
        setDay(day);
        setMonth(month);
        setSlots(slots);
    }

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

    public int getMonthNumber(){
        switch (getMonth()){
            case "ינואר":
                return 1;
            case "פברואר":
                return 2;
            case "מרץ":
                return 3;
            case "אפריל":
                return 4;
            case "מאי":
                return 5;
            case "יוני":
                return 6;
            case "יולי":
                return 7;
            case "אוגוסט":
                return 8;
            case "ספטמבר":
                return 9;
            case "אוקטובר":
                return 10;
            case "נובמבר":
                return 11;
            case "דצמבר":
                return 12;
        }
        return 0;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<OpenHouseSlots> getSlots() {
        return slots;
    }

    public void setSlots(List<OpenHouseSlots> slots) {
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
