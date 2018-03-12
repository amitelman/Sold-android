package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;

/**
 * Created by monkeytech on 8/29/14.
 */
public class PropertyFeaturesCache extends BaseCache<PropertyFeatures> {

    private static PropertyFeaturesCache instance;

    public  static PropertyFeaturesCache getInstance(){
        if (instance == null)
            instance = new PropertyFeaturesCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return PropertyFeatures.class;
    }
}
