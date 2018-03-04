package sold.monkeytech.com.sold_android.framework.models;



import sold.monkeytech.com.sold_android.framework.cache.UserCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by monkey on 08/06/2015.
 */
public class User extends BaseModel<User> {

    private String thumbnail;
    private Boolean isMale;


    private String xmppUserName;
    private Integer xmppId;
    private Boolean isInTheClub;
    private int rank;

    public final static int RANK_NONE = 0;
    public final static int RANK_VIP = 1;
    public final static int RANK_STAFF = 2;
    private String awaitingThumbnail;
    private boolean shouldFixDetials;
    private String registrationComment;

    @Override
    public BaseCache getInstanceOfCache() {
        return UserCache.getInstance();
    }

    @Override
    protected Class getType() {
        return User.class;
    }


}
