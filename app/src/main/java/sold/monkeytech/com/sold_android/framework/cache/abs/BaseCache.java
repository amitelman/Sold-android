package sold.monkeytech.com.sold_android.framework.cache.abs;


import java.util.Collection;
import java.util.LinkedHashMap;

import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

public abstract class BaseCache<T extends BaseModel> {
    protected LinkedHashMap<Long, T> cache = new LinkedHashMap();

    protected BaseCache() {
    }

    public void addObject(T obj) {
        this.cache.put(obj.getId(), obj);
    }

    public T getObjectById(Long id) {
        Class cls = this.getType();
        return this.cache.get(id);
    }

    public void removeObjectById(Long id) {
        this.cache.remove(id);
    }

    public Collection<T> getAllObjects() {
        Class clas = this.getType();
        return this.cache.values();
    }

    public void clear() {
        this.cache.clear();
    }

    public T getObjectWithForceReturn(Long id){
        T object = this.cache.get(id);
        if(object == null){
            object = getNewInstanceOfCacheObject();
            object.setId(id);
        }
        return object;
    }

    protected T getNewInstanceOfCacheObject(){
        final Class<T> entityType = getType();
        T newModel = null;
        try{
            newModel = entityType.newInstance();
        }catch (Exception e){
//            RLog.d("this is an error massage", e.getMessage());
        }
        return newModel;
    }

    protected abstract Class getType();
}
