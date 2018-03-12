package sold.monkeytech.com.sold_android.framework.models;

import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class Price extends BaseModel {

    private String formatted;
    private int value;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return null;
    }

    @Override
    protected Class getType() {
        return Price.class;
    }
}
