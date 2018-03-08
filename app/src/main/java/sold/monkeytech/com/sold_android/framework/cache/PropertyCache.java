package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Property;

/**
 * Created by monkeytech on 8/29/14.
 */
public class PropertyCache extends BaseCache<Property> {
    private static PropertyCache instance;

    public  static PropertyCache getInstance(){
        if (instance == null)
            instance = new PropertyCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Property.class;
    }
}
