package sold.monkeytech.com.sold_android.framework.models;


import sold.monkeytech.com.sold_android.framework.cache.PropertyCache;
import sold.monkeytech.com.sold_android.framework.cache.PropertyTypeCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 05/03/2018.
 */

public class PropertyType extends BaseModel<PropertyType> {

    private String name;
    private String icon;
    private String createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return PropertyTypeCache.getInstance();
    }

    @Override
    protected Class getType() {
        return PropertyType.class;
    }
}
