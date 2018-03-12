package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Address;
import sold.monkeytech.com.sold_android.framework.models.Price;

/**
 * Created by monkeytech on 8/29/14.
 */
public class AddressCache extends BaseCache<Address> {
    private static AddressCache instance;

    public  static AddressCache getInstance(){
        if (instance == null)
            instance = new AddressCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Address.class;
    }
}
