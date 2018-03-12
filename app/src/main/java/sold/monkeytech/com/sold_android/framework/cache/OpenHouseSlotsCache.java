package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;

/**
 * Created by monkeytech on 8/29/14.
 */
public class OpenHouseSlotsCache extends BaseCache<OpenHouseSlots> {

    private static OpenHouseSlotsCache instance;

    public  static OpenHouseSlotsCache getInstance(){
        if (instance == null)
            instance = new OpenHouseSlotsCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return OpenHouseSlots.class;
    }
}
