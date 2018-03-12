package sold.monkeytech.com.sold_android.framework.models;

import java.util.List;

import sold.monkeytech.com.sold_android.framework.cache.PropertyFeaturesCache;
import sold.monkeytech.com.sold_android.framework.cache.abs.BaseCache;
import sold.monkeytech.com.sold_android.framework.models.abs.BaseModel;

/**
 * Created by MonkeyFather on 08/03/2018.
 */

public class PropertyFeatures extends BaseModel<PropertyFeatures> {

    private String featureName;
    private List<Meta> meta;
    private List<String> images;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public List<Meta> getMeta() {
        return meta;
    }

    public void setMeta(List<Meta> meta) {
        this.meta = meta;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public BaseCache getInstanceOfCache() {
        return PropertyFeaturesCache.getInstance();
    }

    @Override
    protected Class getType() {
        return PropertyFeatures.class;
    }
}
