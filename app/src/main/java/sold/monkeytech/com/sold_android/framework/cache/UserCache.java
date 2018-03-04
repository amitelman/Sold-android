package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.User;

/**
 * Created by monkeytech on 8/29/14.
 */
public class UserCache extends BaseCache<User> {
    private static UserCache instance;

    public  static  UserCache getInstance(){
        if (instance == null)
            instance = new UserCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return User.class;
    }
}
