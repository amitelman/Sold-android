package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Category;
import sold.monkeytech.com.sold_android.framework.models.POI;

/**
 * Created by monkeytech on 8/29/14.
 */
public class CategoryCache extends BaseCache<Category> {
    private static CategoryCache instance;

    public  static CategoryCache getInstance(){
        if (instance == null)
            instance = new CategoryCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Category.class;
    }
}
