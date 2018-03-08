package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;

/**
 * Created by monkeytech on 8/29/14.
 */
public class OpenHouseCache extends BaseCache<OpenHouse> {

    private static OpenHouseCache instance;

    public  static OpenHouseCache getInstance(){
        if (instance == null)
            instance = new OpenHouseCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return OpenHouse.class;
    }
}
