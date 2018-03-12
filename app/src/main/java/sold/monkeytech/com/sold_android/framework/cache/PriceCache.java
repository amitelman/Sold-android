package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Price;
import sold.monkeytech.com.sold_android.framework.models.Property;

/**
 * Created by monkeytech on 8/29/14.
 */
public class PriceCache extends BaseCache<Price> {
    private static PriceCache instance;

    public  static PriceCache getInstance(){
        if (instance == null)
            instance = new PriceCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Price.class;
    }
}
