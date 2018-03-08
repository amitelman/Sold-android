package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Property;

/**
 * Created by monkeytech on 8/29/14.
 */
public class PropertyTypeCache extends BaseCache<Property> {
    private static PropertyTypeCache instance;

    public  static PropertyTypeCache getInstance(){
        if (instance == null)
            instance = new PropertyTypeCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Property.class;
    }
}
