package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.POI;
import sold.monkeytech.com.sold_android.framework.models.Price;

/**
 * Created by monkeytech on 8/29/14.
 */
public class POICache extends BaseCache<POI> {
    private static POICache instance;

    public  static POICache getInstance(){
        if (instance == null)
            instance = new POICache();
        return instance;
    }

    @Override
    protected Class getType() {
        return POI.class;
    }
}
