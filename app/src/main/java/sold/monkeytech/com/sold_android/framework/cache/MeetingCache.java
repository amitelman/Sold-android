package sold.monkeytech.com.sold_android.framework.cache;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.Meeting;
import sold.monkeytech.com.sold_android.framework.models.POI;

/**
 * Created by monkeytech on 8/29/14.
 */
public class MeetingCache extends BaseCache<Meeting> {
    private static MeetingCache instance;

    public  static MeetingCache getInstance(){
        if (instance == null)
            instance = new MeetingCache();
        return instance;
    }

    @Override
    protected Class getType() {
        return Meeting.class;
    }
}
