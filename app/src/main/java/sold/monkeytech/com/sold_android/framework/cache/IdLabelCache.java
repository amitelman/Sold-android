package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.IdLabel;
import sold.monkeytech.com.sold_android.framework.models.PropertyFeatures;

/**
 * Created by monkeytech on 8/29/14.
 */
public class IdLabelCache extends BaseCache<IdLabel> {

    private static IdLabelCache instance;

    public  static IdLabelCache getInstance(){
        if (instance == null)
            instance = new IdLabelCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return IdLabel.class;
    }
}
