package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.SavedSearch;

/**
 * Created by monkeytech on 8/29/14.
 */
public class SavedSearchCache extends BaseCache<SavedSearch> {

    private static SavedSearchCache instance;

    public  static SavedSearchCache getInstance(){
        if (instance == null)
            instance = new SavedSearchCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return SavedSearch.class;
    }
}
