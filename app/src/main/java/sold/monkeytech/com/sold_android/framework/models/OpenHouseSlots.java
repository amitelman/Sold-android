package sold.monkeytech.com.sold_android.framework.models;


import java.util.List;

import sold.monkeytech.com.sold_android.framework.cache.OpenHouseCache;
import sold.monkeytech.com.sold_android.framework.cache.OpenHouseSlotsCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class OpenHouseSlots extends BaseModel<OpenHouseSlots> {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return OpenHouseSlotsCache.getInstance();
    }

    @Override
    protected Class getType() {
        return OpenHouseSlots.class;
    }
}
