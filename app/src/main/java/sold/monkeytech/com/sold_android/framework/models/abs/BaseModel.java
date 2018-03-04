package sold.monkeytech.com.sold_android.framework.models.abs;


import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;

/**
 * Created by MonkeyFather on 29/08/2017.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
public abstract class BaseModel<T> {
    protected Object original;
    private Long id;

    public BaseModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract BaseCache getInstanceOfCache();

    public String toString() {
        Class entityType = this.getType();

        try {
            return entityType.getName() + ":" + this.id;
        } catch (Exception var3) {
            return "" + this.id;
        }
    }

    protected abstract Class getType();
}
